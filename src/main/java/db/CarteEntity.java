package db;

import jakarta.persistence.*;

@Entity
@Table(name = "CARTE", schema = "APP", catalog = "")
public class CarteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DISHID")
    private int dishid;
    @Basic
    @Column(name = "PRICE")
    private int price;
    @Basic
    @Column(name = "CATEGORY")
    private String category;
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

        CarteEntity that = (CarteEntity) o;

        if (dishid != that.dishid) return false;
        if (price != that.price) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dishid;
        result = 31 * result + price;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
