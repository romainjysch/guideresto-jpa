package ch.hearc.ig.guideresto.business;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Embeddable
public class Localisation {

    @Column(name = "ADRESSE", nullable = false, length = 100)
    private String street;
    @ManyToOne
    @JoinColumn(name = "FK_VILL")
    private City city;

    public Localisation() {}

    public Localisation(String street, City city) {
        this.street = street;
        this.city = city;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurant.setAddress(this);
        this.city.getRestaurants().add(restaurant);
    }

}