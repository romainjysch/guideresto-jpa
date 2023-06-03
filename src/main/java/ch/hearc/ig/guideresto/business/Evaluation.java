package ch.hearc.ig.guideresto.business;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Evaluation {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVAL")
  @SequenceGenerator(name = "SEQ_EVAL", sequenceName = "SEQ_EVAL", initialValue = 1, allocationSize = 1)
  @Column(name="NUMERO", nullable = false, length = 10)
  private Integer id;
  @Column(name = "DATE_EVAL", nullable = false)
  private LocalDate visitDate;
  @ManyToOne
  @JoinColumn(name = "FK_REST")
  private Restaurant restaurant;

  public Evaluation() {}

  public Evaluation(Integer id, LocalDate visitDate, Restaurant restaurant) {
    this.id = id;
    this.visitDate = visitDate;
    this.restaurant = restaurant;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Evaluation that = (Evaluation) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    RestaurantOverview restaurantOverview = new RestaurantOverview(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getStreet(),
            restaurant.getZipCode(),
            restaurant.getCityName());
    return "Evaluation{" +
            "id=" + id +
            ", visitDate=" + visitDate +
            ", restaurant=" + restaurantOverview +
            '}';
  }

}
