package rest.web;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
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
    @Path("lunch")
    public List<Shift> getLunchShifts(@QueryParam("date") String date) {
        if(date == null) {
            return shiftBean.getAllLunchShifts();
        }
        return shiftBean.getLunchShifts(date);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("dinner")
    public List<Shift> getDinnerShifts(@QueryParam("date") String date) {
        if(date == null) {
            return shiftBean.getAllDinnerShifts();
        }
        return shiftBean.getDinnerShifts(date);
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

    @Inject
    RequestBean requestBean;
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("change-employee")
    public void changeShiftEmployee(ShiftUpdateEmployee shiftUpdateEmployee) {
        Shift shift = shiftBean.changeShiftEmployee((long) shiftUpdateEmployee.getId(), shiftUpdateEmployee.getSsn());
        requestBean.deleteRequests((long) shiftUpdateEmployee.getId()); //deletes other requests of the same shift
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Shift insertShift(Shift shift) {
        return shiftBean.insertShift(shift);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteShift(Shift shift) {
        shiftBean.deleteShift(shift);
        return "ok";
    }

    public static class ShiftUpdateEmployee {
        private int id;
        private String ssn;

        public String getSsn() {
            return ssn;
        }

        public void setSsn(String ssn) {
            this.ssn = ssn;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}