package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantOverview;
import ch.hearc.ig.guideresto.business.RestaurantType;

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
        return getEntityManager().createNamedQuery("Restaurant.researchAll", RestaurantOverview.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    public Restaurant findById(int restaurantId) {
        return getEntityManager().createNamedQuery("Restaurant.researchById", Restaurant.class)
                .setParameter("id", restaurantId)
                .getSingleResult();
    }

    public Set<Restaurant> findByName(String research) {
        return getEntityManager().createNamedQuery("Restaurant.researchByName", Restaurant.class)
                .setParameter("name", "%" + research + "%")
                .getResultStream()
                .collect(Collectors.toSet());
    }

    public Set<Restaurant> findByCityName(String research) {
        return getEntityManager().createNamedQuery("Restaurant.researchByCityName", Restaurant.class)
                .setParameter("cityName", "%" + research + "%")
                .getResultStream()
                .collect(Collectors.toSet());
    }

    public Set<Restaurant> findByRestaurantType(RestaurantType restaurantType) {
        return getEntityManager().createNamedQuery("Restaurant.researchByRestaurantType", Restaurant.class)
                .setParameter("type", restaurantType)
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
