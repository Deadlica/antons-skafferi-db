package db;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.sql.Date;

public class LunchEntityPK implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISHID")
    @Id
    private int dishid;
    @Column(name = "DATE")
    @Id
    private Date date;

    public int getDishid() {
        return dishid;
    }

    public void setDishid(int dishid) {
        this.dishid = dishid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LunchEntityPK that = (LunchEntityPK) o;

        if (dishid != that.dishid) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dishid;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
