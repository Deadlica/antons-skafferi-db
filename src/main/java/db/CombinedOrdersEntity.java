package db;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "COMBINEDORDERS", schema = "APP", catalog = "")
public class CombinedOrdersEntity {
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
    @Basic
    @Column(name = "TIME")
    private Time time;
    @Basic
    @Column(name = "PRICE")
    private int price;
    @Basic
    @Column(name = "CATEGORY")
    private String category;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CombinedOrdersEntity that = (CombinedOrdersEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (served != that.served) return false;
        if (price != that.price) return false;
        if (dishid != null ? !dishid.equals(that.dishid) : that.dishid != null) return false;
        if (bookingid != null ? !bookingid.equals(that.bookingid) : that.bookingid != null) return false;
        if (employeeid != null ? !employeeid.equals(that.employeeid) : that.employeeid != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

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
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
