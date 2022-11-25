package rest.web;

public class ShiftUpdateEmployee {

    private int id;

    private String ssn;

    public ShiftUpdateEmployee(int shiftId, String toId) {
        this.id = shiftId;
        this.ssn = toId;
    }

    public ShiftUpdateEmployee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
