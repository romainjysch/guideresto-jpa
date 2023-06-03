package ch.hearc.ig.guideresto.business;

import lombok.Getter;
import lombok.Setter;
import utils.BooleanConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "LIKES")
public class BasicEvaluation extends Evaluation {

  @Column(name = "APPRECIATION", nullable = false, length = 1)
  @Convert(converter = BooleanConverter.class)
  private boolean likeRestaurant;
  @Column(name = "ADRESSE_IP", nullable = false, length = 100)
  private String ipAddress;

  public BasicEvaluation() {}

  public BasicEvaluation(Integer id, LocalDate visitDate, Restaurant restaurant, boolean likeRestaurant,
      String ipAddress) {
    super(id, visitDate, restaurant);
    this.likeRestaurant = likeRestaurant;
    this.ipAddress = ipAddress;
  }

  @Override
  public String toString() {
    return super.toString() + " BasicEvaluation{" +
            "likeRestaurant=" + likeRestaurant +
            ", ipAddress='" + ipAddress + '\'' +
            '}';
  }

}
