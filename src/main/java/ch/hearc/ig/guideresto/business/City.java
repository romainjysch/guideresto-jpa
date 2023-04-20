package ch.hearc.ig.guideresto.business;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="VILLES")
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
    @Transient
    private Set<Restaurant> restaurants;

    public City() {

    }

    public City(Integer id, String zipCode, String cityName) {
        this.id = id;
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.restaurants = new HashSet<>();
    }
    
    public String getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

  public void setId(int id) {
        this.id = id;
  }

  public int getId() {
    return id;
  }
}