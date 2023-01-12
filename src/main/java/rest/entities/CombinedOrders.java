package rest.entities;

import jakarta.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "COMBINEDORDERS")
public class CombinedOrders {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "dishID", nullable = false)
    private DishView dish;
    @ManyToOne
    @JoinColumn(name = "bookingID", nullable = false)
    private Booking booking;
    @ManyToOne
    @JoinColumn(name = "employeeID", nullable = false)
    private EmployeeView employee;
    @Column(name = "notes")
    private String notes;
    @Column(name = "status", nullable = false)
    private Boolean status;
    @Column(name = "served", nullable = false)
    private Boolean served;
    @Column(name = "time")
    private String time;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "description", nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DishView getDish() {
        return dish;
    }

    public void setDish(DishView dish) {
        this.dish = dish;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public EmployeeView getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeView employee) {
        this.employee = employee;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getServed() {
        return served;
    }

    public void setServed(Boolean served) {
        this.served = served;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
}
