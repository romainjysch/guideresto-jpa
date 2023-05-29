package ch.hearc.ig.guideresto.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.function.Supplier;

public class DBTransaction implements AutoCloseable {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public DBTransaction() {
        emf = Persistence.createEntityManagerFactory("guideRestoPersistenceUnit");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public EntityManager getEm() {
        return em;
    }

    public <T> T inTransaction(Supplier<T> supplier) {
        T returnValue = supplier.get();
        em.getTransaction().commit();
        return returnValue;
    }

    public void inTransaction(Runnable runnable) {
        runnable.run();
        em.getTransaction().commit();
    }

    @Override
    public void close() {
        em.close();
    }

}
