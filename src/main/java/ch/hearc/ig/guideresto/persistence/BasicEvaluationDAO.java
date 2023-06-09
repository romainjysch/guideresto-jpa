package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.BasicEvaluation;

public class BasicEvaluationDAO {

    public void insert(BasicEvaluation basicEvaluation) {
        getEntityManager().persist(basicEvaluation);
    }

}
