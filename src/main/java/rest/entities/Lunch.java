package rest.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "LUNCH")
public class Lunch implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "dishID", nullable = false)
    private Dish dish;

    @Column(name = "price", nullable = false)
    private Long price;

    @Id
    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "description", nullable = false)
    private String description;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
