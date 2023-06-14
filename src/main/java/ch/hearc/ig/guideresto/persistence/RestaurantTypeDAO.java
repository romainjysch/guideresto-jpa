package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.RestaurantType;

import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantTypeDAO {

    private static RestaurantTypeDAO instance;

    private RestaurantTypeDAO() {}

    public static RestaurantTypeDAO getInstance() {
        if (instance == null) {
            instance = new RestaurantTypeDAO();
        }
        return instance;
    }

    public Set<RestaurantType> findAll() {
        return getEntityManager().createNamedQuery("researchAllRestaurantTypes", RestaurantType.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

}
