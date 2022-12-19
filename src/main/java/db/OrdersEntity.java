package db;

import jakarta.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "ORDERS", schema = "APP", catalog = "")
public class OrdersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "DISHID")
    private Integer dishid;
    @Basic
    @Column(name = "BOOKINGID")
    private Integer bookingid;
    @Basic
    @Column(name = "EMPLOYEEID")
    private String employeeid;
    @Basic
    @Column(name = "NOTES")
    private String notes;
    @Basic
    @Column(name = "STATUS")
    private boolean status;
    @Basic
    @Column(name = "SERVED")
    private boolean served;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDishid() {
        return dishid;
    }

    public void setDishid(Integer dishid) {
        this.dishid = dishid;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (served != that.served) return false;
        if (dishid != null ? !dishid.equals(that.dishid) : that.dishid != null) return false;
        if (bookingid != null ? !bookingid.equals(that.bookingid) : that.bookingid != null) return false;
        if (employeeid != null ? !employeeid.equals(that.employeeid) : that.employeeid != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dishid != null ? dishid.hashCode() : 0);
        result = 31 * result + (bookingid != null ? bookingid.hashCode() : 0);
        result = 31 * result + (employeeid != null ? employeeid.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (served ? 1 : 0);
        return result;
    }
}
