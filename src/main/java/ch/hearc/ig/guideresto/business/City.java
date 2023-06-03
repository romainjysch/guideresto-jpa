package ch.hearc.ig.guideresto.business;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="VILLES")
@NamedQuery(name = "researchAllCities", query = "select c from City c")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VILLES")
    @SequenceGenerator(name = "SEQ_VILLES", sequenceName = "SEQ_VILLES", initialValue = 1, allocationSize = 1)
    @Column(name="NUMERO", nullable = false, length = 10)
    private Integer id;
    @Column(name="CODE_POSTAL", nullable = false, length = 100)
    private String zipCode;
    @Column(name="NOM_VILLE", nullable = false, length = 100)
    private String cityName;
    @OneToMany(mappedBy = "address.city")
    private Set<Restaurant> restaurants;

    public City() {}

    public City(String zipCode, String cityName) {
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.restaurants = new HashSet<>();
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", zipCode='" + zipCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", restaurants=" + restaurants +
                '}';
    }

}