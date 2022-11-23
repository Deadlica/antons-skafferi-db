package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Variant;
import rest.entities.Shift;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Path("/shift")
public class ShiftResource {
    @Inject
    ShiftBean shiftBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shift> getAllShift(@QueryParam("employeeId") String employeeId, @QueryParam("shiftId") Integer shiftId) {
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
}