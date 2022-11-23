package db;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class RequestEntityPK implements Serializable {
    @Column(name = "SHIFTID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shiftid;
    @Column(name = "TOID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String toid;

    public int getShiftid() {
        return shiftid;
    }

    public void setShiftid(int shiftid) {
        this.shiftid = shiftid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestEntityPK that = (RequestEntityPK) o;

        if (shiftid != that.shiftid) return false;
        if (toid != null ? !toid.equals(that.toid) : that.toid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shiftid;
        result = 31 * result + (toid != null ? toid.hashCode() : 0);
        return result;
    }
}
