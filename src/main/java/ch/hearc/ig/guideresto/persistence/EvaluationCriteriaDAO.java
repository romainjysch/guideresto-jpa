package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;

import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

public class EvaluationCriteriaDAO {

    private static EvaluationCriteriaDAO instance;

    private EvaluationCriteriaDAO() {}

    public static EvaluationCriteriaDAO getInstance() {
        if (instance == null) {
            instance = new EvaluationCriteriaDAO();
        }
        return instance;
    }

    public Set<EvaluationCriteria> findAll() {
        return getEntityManager().createNamedQuery("researchAllEvaluationCriteria", EvaluationCriteria.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

}
