package ch.hearc.ig.guideresto.business;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LIKES")
public class BasicEvaluation extends Evaluation {

    // La gestion du boolean demande un tout petit peu plus que ça
  @Column(name = "APPRECIATION", nullable = false, length = 1)
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

  public Boolean isLikeRestaurant() {
    return likeRestaurant;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  @Override
  public String toString() {
    return super.toString() + " BasicEvaluation{" +
            "likeRestaurant=" + likeRestaurant +
            ", ipAddress='" + ipAddress + '\'' +
            '}';
  }

}
