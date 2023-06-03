package ch.hearc.ig.guideresto.business;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "NOTES")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTES")
    @SequenceGenerator(name = "SEQ_NOTES", sequenceName = "SEQ_NOTES", initialValue = 1, allocationSize = 1)
    @Column(name="NUMERO", nullable = false, length = 10)
    private Integer id;
    @Column(name="NOTE", nullable = false, length = 3)
    private Integer grade;
    @ManyToOne
    @JoinColumn(name = "FK_COMM")
    private CompleteEvaluation evaluation;
    @ManyToOne
    @JoinColumn(name = "FK_CRIT")
    private EvaluationCriteria criteria;

    public Grade() {}

    public Grade(Integer id, Integer grade, CompleteEvaluation evaluation, EvaluationCriteria criteria) {
        this.id = id;
        this.grade = grade;
        this.evaluation = evaluation;
        this.criteria = criteria;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                ", evaluation=" + evaluation +
                ", criteria=" + criteria +
                '}';
    }

}