package ch.hearc.ig.guideresto.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Supplier;

public class Database implements AutoCloseable {

    private static Database instance;
    private final EntityManagerFactory emf;
    private static ThreadLocal<EntityManager> em = new ThreadLocal<>();

    private Database() {
        emf = Persistence.createEntityManagerFactory("guideRestoPersistenceUnit");
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public <T> T inTransaction(Supplier<T> function) {
        EntityManager em = emf.createEntityManager();
        try {
            Database.em.set(em);
            em.getTransaction().begin();
            T t = function.get();
            em.getTransaction().commit();
            return t;
        } finally {
            em.close();
            Database.em.remove();
        }
    }

    public void inTransaction(Runnable function) {
        EntityManager em = emf.createEntityManager();
        try {
            Database.em.set(em);
            em.getTransaction().begin();
            function.run();
            em.getTransaction().commit();
        } finally {
            em.close();
            Database.em.remove();
        }
    }

    public static EntityManager getEntityManager() {
        return em.get();
    }

    @Override
    public void close() {
        try {
            emf.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
