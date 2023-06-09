package ch.hearc.ig.guideresto.business;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    public void addRestaurant(Restaurant restaurant) {
        restaurant.setType(this);
        restaurants.add(restaurant);
    }

    // label est un invariant
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantType that = (RestaurantType) o;
        return Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}