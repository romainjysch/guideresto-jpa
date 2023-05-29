package ch.hearc.ig.guideresto.business;

import javax.persistence.*;

@Entity
@Table(name = "CRITERES_EVALUATION")
@NamedQuery(name = "researchAllEvaluationCriteria", query = "select ec from EvaluationCriteria ec")
public class EvaluationCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CRITERES_EVALUATION")
    @SequenceGenerator(name = "SEQ_CRITERES_EVALUATION", sequenceName = "SEQ_CRITERES_EVALUATION", initialValue = 1, allocationSize = 1)
    @Column(name="NUMERO", nullable = false, length = 10)
    private Integer id;
    @Column(name="NOM", nullable = false, length = 100)
    private String name;
    @Column(name="DESCRIPTION", nullable = false, length = 512)
    private String description;

    public EvaluationCriteria() {}

    public EvaluationCriteria(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "EvaluationCriteria{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}