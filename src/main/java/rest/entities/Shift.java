package rest.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "SHIFT")
public class Shift implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "employeeID", nullable = false)
    private WorkingEmployees employee;
    @Column(name = "beginTime", nullable = false)
    private String beginTime;
    @Column(name = "endTime", nullable = false)
    private String endTime;
    @Column(name = "date", nullable = false)
    private String date;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public WorkingEmployees getEmployee() {
        return employee;
    }
    public void setEmployee(WorkingEmployees employee) {
        this.employee = employee;
    }
    public String getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String beginDate) {
        this.date = beginDate;
    }
}
