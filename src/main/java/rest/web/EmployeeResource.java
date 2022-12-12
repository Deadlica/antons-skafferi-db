package rest.web;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Employee;
import rest.entities.EmployeeView;
import rest.entities.RetiredEmployees;
import rest.entities.WorkingEmployees;

import javax.print.attribute.standard.Media;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/employee")
public class EmployeeResource {
    @Inject
    EmployeeBean employeeBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeView> getAllEmployee(@QueryParam("id") String id) {
        if(id == null || id.isEmpty()) {
            return employeeBean.getAllEmployees();
        }

        return employeeBean.getEmployee(id);
    }

    @GET
    @Path("working")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WorkingEmployees> getWorkingEmployees() {
        return employeeBean.getWorkingEmployees();
    }

    @GET
    @Path("retired")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RetiredEmployees> getRetiredEmployees() {
        return employeeBean.getRetiredEmployees();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("lunch/available")
    public List<EmployeeView> getAvailableLunchEmployees(@QueryParam("date") String date) {
        String time = "11:00:00";
        if(date == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            return employeeBean.getAvailableEmployees(simpleDateFormat.format(today), time);
        }
        return employeeBean.getAvailableEmployees(date, time);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("dinner/available")
    public List<EmployeeView> getAvailableDinnerEmployees(@QueryParam("date") String date) {
        String time = "16:00:00";
        if(date == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            return employeeBean.getAvailableEmployees(simpleDateFormat.format(today), time);
        }
        return employeeBean.getAvailableEmployees(date, time);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Employee insertEmployee(Employee employee) {
        return employeeBean.insertEmployee(employee);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteEmployee(Employee employee) {
        employeeBean.deleteEmployee(employee);
        return "ok";
    }

    @PUT
    @Path("unretire")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String unretireEmployee(Employee employee) {
        employeeBean.unretireEmployee(employee);
        return "ok";
    }
}