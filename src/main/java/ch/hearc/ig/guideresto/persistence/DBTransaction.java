package ch.hearc.ig.guideresto.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.function.Supplier;

public class DBTransaction implements AutoCloseable {

    private final EntityManagerFactory emf;

    public DBTransaction() {
        emf = Persistence.createEntityManagerFactory("guideRestoPersistenceUnit");
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public <T> T inTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

    public void inTransaction(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void close() {
        emf.close();
    }

}
