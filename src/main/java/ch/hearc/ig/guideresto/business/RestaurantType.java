package ch.hearc.ig.guideresto.business;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TYPES_GASTRONOMIQUES")
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
    @Transient
    private Set<Restaurant> restaurants;

    public RestaurantType() {}

    public RestaurantType(Integer id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.restaurants = new HashSet<>();
    }
    
    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int getId() {
        return id;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}