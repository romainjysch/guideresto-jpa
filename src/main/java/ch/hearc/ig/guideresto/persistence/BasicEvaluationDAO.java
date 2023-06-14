package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.BasicEvaluation;

public class BasicEvaluationDAO {

    private static BasicEvaluationDAO instance;

    private BasicEvaluationDAO() {}

    public static BasicEvaluationDAO getInstance() {
        if (instance == null) {
            instance = new BasicEvaluationDAO();
        }
        return instance;
    }

    public void insert(BasicEvaluation basicEvaluation) {
        getEntityManager().persist(basicEvaluation);
    }

    public void delete(BasicEvaluation basicEvaluation) {
        getEntityManager().remove(basicEvaluation);
    }

}
