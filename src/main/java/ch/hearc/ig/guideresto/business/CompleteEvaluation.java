package ch.hearc.ig.guideresto.business;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "COMMENTAIRES")
public class CompleteEvaluation extends Evaluation {

  @Column(name = "COMMENTAIRE", nullable = false)
  @Lob
  private String comment;
  @Column(name = "NOM_UTILISATEUR", nullable = false, length = 100)
  private String username;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "evaluation")
  private Set<Grade> grades;

  public CompleteEvaluation() {}

  public CompleteEvaluation(Integer id, LocalDate visitDate, Restaurant restaurant, String comment,
      String username) {
    super(id, visitDate, restaurant);
    this.comment = comment;
    this.username = username;
    this.grades = new HashSet<>();
  }

  public void addGrade(Grade grade) {
    grade.setEvaluation(this);
    this.getGrades().add(grade);
  }

  @Override
  public String toString() {
    return super.toString() + "CompleteEvaluation{" +
            "comment='" + comment + '\'' +
            ", username='" + username + '\'' +
            ", grades=" + grades +
            '}';
  }

}
