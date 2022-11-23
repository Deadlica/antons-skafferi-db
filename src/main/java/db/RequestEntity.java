package db;

import jakarta.persistence.*;

@Entity
@Table(name = "REQUEST", schema = "APP", catalog = "")
@IdClass(RequestEntityPK.class)
public class RequestEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SHIFTID")
    private int shiftid;
    @Id
    @Column(name = "TOID")
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

        RequestEntity that = (RequestEntity) o;

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
