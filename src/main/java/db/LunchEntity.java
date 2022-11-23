package db;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "LUNCH", schema = "APP", catalog = "")
public class LunchEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DISHID")
    private int dishid;
    @Basic
    @Column(name = "PRICE")
    private int price;
    @Basic
    @Column(name = "DATE")
    private Date date;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    public int getDishid() {
        return dishid;
    }

    public void setDishid(int dishid) {
        this.dishid = dishid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

        LunchEntity that = (LunchEntity) o;

        if (dishid != that.dishid) return false;
        if (price != that.price) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dishid;
        result = 31 * result + price;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
