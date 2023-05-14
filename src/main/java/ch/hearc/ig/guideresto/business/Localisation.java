package ch.hearc.ig.guideresto.business;

import javax.persistence.*;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}