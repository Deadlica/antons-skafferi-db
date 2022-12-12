package rest.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "REQUEST")
public class Request implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "shiftID")
    Shift shift;

    @Id
    @ManyToOne
    @JoinColumn(name = "toID")
    WorkingEmployees toEmployee;

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public WorkingEmployees getToEmployee() {
        return toEmployee;
    }

    public void setToEmployee(WorkingEmployees toEmployee) {
        this.toEmployee = toEmployee;
    }
}
