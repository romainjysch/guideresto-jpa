package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;

import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

public class EvaluationCriteriaDAO {

    public Set<EvaluationCriteria> findAll() {
        TypedQuery<EvaluationCriteria> query = getEntityManager().createNamedQuery("researchAllEvaluationCriteria", EvaluationCriteria.class);
        return query.getResultStream().collect(Collectors.toSet());
    }

}
