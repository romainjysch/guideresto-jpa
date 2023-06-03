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
@Table(name = "RESTAURANTS")
@NamedQuery(name = "researchAllRestaurants", query = "select r from Restaurant r")
@NamedQuery(name = "researchRestaurantById", query = "select r from Restaurant r where r.id = ?1")
@NamedQuery(name = "researchRestaurantsByName", query = "select r from Restaurant r where r.name like ?1")
@NamedQuery(name = "researchRestaurantsByCityName", query = "select r from Restaurant r where r.address.city.cityName like ?1")
@NamedQuery(name = "researchRestaurantsByType", query = "select r from Restaurant r where r.type = ?1")
public class Restaurant implements IAmRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESTAURANTS")
    @SequenceGenerator(name = "SEQ_RESTAURANTS", sequenceName = "SEQ_RESTAURANTS", initialValue = 1, allocationSize = 1)
    @Column(name="NUMERO", nullable = false, length = 10)
    private Integer id;
    @Column(name="NOM", nullable = false, length = 100)
    private String name;
    @Column(name="DESCRIPTION", nullable = false)
    @Lob
    private String description;
    @Column(name="SITE_WEB", nullable = false, length = 100)
    private String website;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Evaluation> evaluations;
    @Embedded
    private Localisation address;
    @ManyToOne
    @JoinColumn(name = "FK_TYPE")
    private RestaurantType type;

    public Restaurant() {}

    public Restaurant(Integer id, String name, String description, String website, String street, City city, RestaurantType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.website = website;
        this.evaluations = new HashSet<>();
        this.address = new Localisation(street, city);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getZipCode() {
        return address.getCity().getZipCode();
    }

    public String getStreet() {
        return address.getStreet();
    }

    public String getCityName() {
        return address.getCity().getCityName();
    }

    public void addEvaluation(Evaluation evaluation) {
        evaluation.setRestaurant(this);
        this.getEvaluations().add(evaluation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}