package ch.hearc.ig.guideresto.business;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "TYPES_GASTRONOMIQUES")
@NamedQuery(name = "researchAllRestaurantTypes", query = "select rt from RestaurantType rt")
public class RestaurantType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TYPES_GASTRONOMIQUES")
    @SequenceGenerator(name = "SEQ_TYPES_GASTRONOMIQUES", sequenceName = "SEQ_TYPES_GASTRONOMIQUES", initialValue = 1, allocationSize = 1)
    @Column(name="NUMERO", nullable = false, length = 10)
    private Integer id;
    @Column(name="LIBELLE", nullable = false, length = 100)
    private String label;
    @Column(name="DESCRIPTION", nullable = false)
    @Lob
    private String description;
    @OneToMany(mappedBy = "type")
    private Set<Restaurant> restaurants;

    public RestaurantType() {}

    public RestaurantType(Integer id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.restaurants = new HashSet<>();
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurant.setType(this);
        this.getRestaurants().add(restaurant);
    }

    @Override
    public String toString() {
        return "RestaurantType{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", restaurants=" + restaurants +
                '}';
    }

}