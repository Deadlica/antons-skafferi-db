package rest.web;

import jakarta.enterprise.inject.Typed;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import rest.entities.Employee;
import rest.entities.Request;
import rest.entities.Shift;

import java.util.ArrayList;
import java.util.List;

@Named
@Transactional
public class EmployeeBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Employee> getAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public List<Employee> getEmployee(String id) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.ssn = :id", Employee.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Employee> getAvailableEmployees(String date) {
        TypedQuery<Employee> workingEmployeesSsn = em.createQuery("SELECT s.employee.ssn FROM Shift s WHERE s.date = :date", Employee.class);
        workingEmployeesSsn.setParameter("date", date);
        List<Employee> employeesWorking = workingEmployeesSsn.getResultList();
        List<Employee> employeesNotWorking;

        String stringQuery = buildAvailableEmployeesQueryString(employeesWorking);
        TypedQuery<Employee> availableEmployeesQuery = em.createQuery(stringQuery, Employee.class);

        for(int index = 0; index < employeesWorking.size(); index++) {
            availableEmployeesQuery.setParameter("ssn" + index, employeesWorking.get(index));
        }

        return availableEmployeesQuery.getResultList();
    }

    public Employee insertEmployee(Employee employee) {
        em.persist(employee);
        return employee;
    }

    @Inject
    ShiftBean shiftBean;
    @Inject
    RequestBean requestBean;
    public void deleteEmployee(Employee employee) {
        requestBean.deleteRequestsOfEmployee(employee);
        shiftBean.deleteAllShiftsOfEmployee(employee);
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.ssn = :ssn", Employee.class);
        query.setParameter("ssn", employee.getSsn());
        em.remove(query.getSingleResult());
    }

    private String buildAvailableEmployeesQueryString(List<Employee> employeesWorking) {
        StringBuilder availableEmployeesQuery = new StringBuilder("SELECT e FROM Employee e");
        if(!employeesWorking.isEmpty()) {
            availableEmployeesQuery.append(" WHERE ");
        }
        for(int index = 0; index < employeesWorking.size(); index++) {
            String ssnPlaceholder = "ssn" + index;
            if(index < employeesWorking.size() - 1) {
                availableEmployeesQuery.append("e.ssn <> :" + ssnPlaceholder + " AND ");
            }
            else {
                availableEmployeesQuery.append("e.ssn <> :" + ssnPlaceholder);
            }
        }
        return availableEmployeesQuery.toString();
    }
}