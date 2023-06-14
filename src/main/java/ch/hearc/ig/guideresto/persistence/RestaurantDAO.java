package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantOverview;
import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.utils.RestaurantToRestaurantOverview;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantDAO {

    private static RestaurantDAO instance;

    private RestaurantDAO() {}

    public static RestaurantDAO getInstance() {
        if (instance == null) {
            instance = new RestaurantDAO();
        }
        return instance;
    }

    public Set<RestaurantOverview> findAll () {
        Set<RestaurantOverview> restaurantOverviews = new HashSet<>();
        for (Restaurant restaurant : getEntityManager().createNamedQuery("researchAllRestaurants", Restaurant.class).getResultList()) {
            RestaurantOverview restaurantOverview = RestaurantToRestaurantOverview.convert(restaurant);
            restaurantOverviews.add(restaurantOverview);
        }
        return restaurantOverviews;
    }

    public Restaurant findById(int restaurantId) {
        return getEntityManager().createNamedQuery("researchRestaurantById", Restaurant.class)
                .setParameter(1, restaurantId)
                .getSingleResult();
    }

    public Set<Restaurant> findByName(String research) {
        return getEntityManager().createNamedQuery("researchRestaurantsByName", Restaurant.class)
                .setParameter(1, "%" + research + "%")
                .getResultStream()
                .collect(Collectors.toSet());
    }

    public Set<Restaurant> findByCityName(String research) {
        return getEntityManager().createNamedQuery("researchRestaurantsByCityName", Restaurant.class)
                .setParameter(1, "%" + research + "%")
                .getResultStream()
                .collect(Collectors.toSet());
    }

    public Set<Restaurant> findByRestaurantType(RestaurantType restaurantType) {
        return getEntityManager().createNamedQuery("researchRestaurantsByType", Restaurant.class)
                .setParameter(1, restaurantType)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    public void insert(Restaurant restaurant) {
        getEntityManager().persist(restaurant);
    }

    public void update(Restaurant restaurant) {
        getEntityManager().merge(restaurant);
    }

    public void delete(Restaurant restaurant) {
        getEntityManager().remove(restaurant);
    }

}
