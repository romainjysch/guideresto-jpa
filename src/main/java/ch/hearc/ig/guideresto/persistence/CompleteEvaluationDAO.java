package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.CompleteEvaluation;

public class CompleteEvaluationDAO {

    private static CompleteEvaluationDAO instance;

    private CompleteEvaluationDAO() {}

    public static CompleteEvaluationDAO getInstance() {
        if (instance == null) {
            instance = new CompleteEvaluationDAO();
        }
        return instance;
    }

    public void insert(CompleteEvaluation completeEvaluation) {
        getEntityManager().persist(completeEvaluation);
    }

}
