package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.RestaurantType;

import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantTypeDAO {

    public Set<RestaurantType> findAll() {
        TypedQuery<RestaurantType> query = getEntityManager().createNamedQuery("researchAllRestaurantTypes", RestaurantType.class);
        return query.getResultStream().collect(Collectors.toSet());
    }

}
