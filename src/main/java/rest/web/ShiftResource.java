package rest.web;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Shift;

import java.util.*;

@Path("/shift")
public class ShiftResource {
    @Inject
    ShiftBean shiftBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shift> getAllShift(@QueryParam("employeeId") String employeeId, @QueryParam("shiftId") Integer shiftId, @QueryParam("date") String date) {
        if(date != null && employeeId == null && shiftId == null) {
            return shiftBean.getShift(date);
        }
        if(employeeId == null && shiftId == null) { //All shifts
            return shiftBean.getAllShifts();
        }
        if(employeeId != null && shiftId == null) { //Shifts for specific employee
            return shiftBean.getShiftsOfEmployee(employeeId);
        }
        if(employeeId == null && shiftId != null) { //Shift with specific id
            return shiftBean.getShift(shiftId);
        }

        return shiftBean.getShift(shiftId, employeeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("upcoming-shifts")
    public List<Shift> getUpcomingShifts(@QueryParam("id") String id, @QueryParam("date") String date) {
        if(id != null && date != null) {
            return shiftBean.getUpcomingShifts(id, date);
        }
        return null;
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("change-employee")
    public Shift changeShiftEmployee(@Valid ShiftUpdateEmployee shiftUpdateEmployee, @HeaderParam("shiftId") String id) {

        return shiftBean.changeShiftEmployee((long) shiftUpdateEmployee.getId(), shiftUpdateEmployee.getSsn());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Shift insertShift(Shift shift) {
        return shiftBean.insertShift(shift);
    }
}