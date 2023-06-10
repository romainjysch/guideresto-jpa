package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantOverview;
import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.utils.RestaurantToRestaurantOverview;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
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
        TypedQuery<Restaurant> query = getEntityManager().createNamedQuery("researchAllRestaurants", Restaurant.class);
        List<Restaurant> restaurants = query.getResultList();
        Set<RestaurantOverview> restaurantOverviews = new HashSet<>();
        for (Restaurant restaurant : restaurants) {
            RestaurantOverview restaurantOverview = RestaurantToRestaurantOverview.convert(restaurant);
            restaurantOverviews.add(restaurantOverview);
        }
        return restaurantOverviews;
    }

    public Restaurant findById(int restaurantId) {
        return getEntityManager().find(Restaurant.class, restaurantId);
    }

    public Set<Restaurant> findByName(String research) {
        TypedQuery<Restaurant> query = getEntityManager().createNamedQuery("researchRestaurantsByName", Restaurant.class)
                .setParameter(1, research + "%");
        return query.getResultStream().collect(Collectors.toSet());
    }

    public Set<Restaurant> findByCityName(String research) {
        TypedQuery<Restaurant> query = getEntityManager().createNamedQuery("researchRestaurantsByCityName", Restaurant.class)
                .setParameter(1, research + "%");
        return query.getResultStream().collect(Collectors.toSet());
    }

    public Set<Restaurant> findByRestaurantType(RestaurantType restaurantType) {
        TypedQuery<Restaurant> query = getEntityManager().createNamedQuery("researchRestaurantsByType", Restaurant.class)
                .setParameter(1, restaurantType);
        return query.getResultStream().collect(Collectors.toSet());
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
