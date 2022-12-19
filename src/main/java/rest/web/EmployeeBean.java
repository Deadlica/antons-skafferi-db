package rest.web;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Employee;
import rest.entities.EmployeeView;
import rest.entities.RetiredEmployees;
import rest.entities.WorkingEmployees;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Named
@Transactional
public class EmployeeBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<EmployeeView> getAllEmployees() {
        TypedQuery<EmployeeView> query = em.createQuery("SELECT e FROM EmployeeView e", EmployeeView.class);
        return query.getResultList();
    }

    public List<WorkingEmployees> getWorkingEmployees() {
        TypedQuery<WorkingEmployees> query = em.createQuery("SELECT w FROM WorkingEmployees w", WorkingEmployees.class);
        return query.getResultList();
    }

    public List<RetiredEmployees> getRetiredEmployees() {
        TypedQuery<RetiredEmployees> query = em.createQuery("SELECT r FROM RetiredEmployees r", RetiredEmployees.class);
        return query.getResultList();
    }

    public List<EmployeeView> getEmployee(String id) {
        TypedQuery<EmployeeView> query = em.createQuery("SELECT w FROM WorkingEmployees w WHERE w.ssn = :id", EmployeeView.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<EmployeeView> getAvailableEmployees(String date, String time) {
        TypedQuery<EmployeeView> query = em.createQuery("SELECT w FROM WorkingEmployees w WHERE w.ssn NOT IN (SELECT s.employee.ssn FROM Shift s WHERE s.date = :date AND s.beginTime = :time)", EmployeeView.class);
        query.setParameter("date", date);
        query.setParameter("time", time);
        return query.getResultList();
    }

    public void updateEmployee(Employee employee) {
        Employee employeeItem = em.find(Employee.class, employee.getSsn());
        employeeItem.setFirstName(employee.getFirstName());
        employeeItem.setLastName(employee.getLastName());
        employeeItem.setEmail(employee.getEmail());
        employeeItem.setPhoneNumber(employee.getPhoneNumber());
    }

    public Employee insertEmployee(Employee employee) {
        employee.setWorking(true);
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
        Employee emp = em.find(Employee.class, employee.getSsn());
        emp.setWorking(false);
    }

    public void unretireEmployee(Employee employee) {
        Employee emp = em.find(Employee.class, employee.getSsn());
        emp.setWorking(true);
    }

    private String buildAvailableEmployeesQueryString(List<WorkingEmployees> employeesWorking) {
        StringBuilder availableEmployeesQuery = new StringBuilder("SELECT w FROM WorkingEmployees w");
        if(!employeesWorking.isEmpty()) {
            availableEmployeesQuery.append(" WHERE ");
        }
        for(int index = 0; index < employeesWorking.size(); index++) {
            String ssnPlaceholder = "ssn" + index;
            if(index < employeesWorking.size() - 1) {
                availableEmployeesQuery.append("w.ssn <> :" + ssnPlaceholder + " AND ");
            }
            else {
                availableEmployeesQuery.append("w.ssn <> :" + ssnPlaceholder);
            }
        }
        return availableEmployeesQuery.toString();
    }
}