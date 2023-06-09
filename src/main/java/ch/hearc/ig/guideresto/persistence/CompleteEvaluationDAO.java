package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.CompleteEvaluation;

public class CompleteEvaluationDAO {

    public void insert(CompleteEvaluation completeEvaluation) {
        getEntityManager().persist(completeEvaluation);
    }

}
