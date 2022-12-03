package rest.web;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Employee;

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
    public List<Employee> getAllEmployee(@QueryParam("id") String id) {
        if(id == null) {
            return employeeBean.getAllEmployees();
        }

        return employeeBean.getEmployee(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("available")
    public List<Employee> getAvailableEmployess(@QueryParam("date") String date) {
        if(date == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            return employeeBean.getAvailableEmployees(simpleDateFormat.format(today));
        }
        return employeeBean.getAvailableEmployees(date);
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
}