package rest.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "CARTE")
public class Carte implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "dishID", nullable = false)
    private DishView dish;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description", nullable = false)
    private String description;

    public DishView getDish() {
        return dish;
    }

    public void setDish(DishView dish) {
        this.dish = dish;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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
