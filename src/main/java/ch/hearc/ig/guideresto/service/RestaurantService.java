package ch.hearc.ig.guideresto.service;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantOverview;
import ch.hearc.ig.guideresto.business.RestaurantType;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.persistence.Entity;
import utils.RestaurantToRestaurantOverview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantService {

    private final EntityManagerFactory emf;

    public RestaurantService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Set<RestaurantOverview> researchAllRestaurants() {
        return inTransaction(em -> {
            TypedQuery<Restaurant> query = em.createNamedQuery("researchAllRestaurants",
                Restaurant.class);
            List<Restaurant> restaurants = query.getResultList();
            Set<RestaurantOverview> restaurantOverviews = new HashSet<>();
            for (Restaurant restaurant : restaurants) {
                RestaurantOverview restaurantOverview = RestaurantToRestaurantOverview.convert(
                    restaurant);
                restaurantOverviews.add(restaurantOverview);
            }
            return restaurantOverviews;
        });
    }

    public Restaurant researchRestaurantById(int restaurantId) {
        return inTransaction(em -> {
            TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantById",
                    Restaurant.class)
                .setParameter(1, restaurantId);
            return query.getSingleResult();
        });
    }

    public Set<Restaurant> researchRestaurantsByName(String research) {
        return inTransaction(em -> {
            TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByName",
                    Restaurant.class)
                .setParameter(1, research + "%");
            return query.getResultStream().collect(Collectors.toSet());
        });
    }

    public Set<Restaurant> researchRestaurantsByCityName(String research) {
        return inTransaction(em -> {
            TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByCityName",
                    Restaurant.class)
                .setParameter(1, research + "%");
            return query.getResultStream().collect(Collectors.toSet());
        });
    }

    public Set<Restaurant> researchRestaurantsByType(RestaurantType restaurantType) {
        return inTransaction(em -> {
            TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByType",
                    Restaurant.class)
                .setParameter(1, restaurantType);
            return query.getResultStream().collect(Collectors.toSet());
        });
    }

    public Set<RestaurantType> researchAllRestaurantTypes() {
        return inTransaction(em -> {
            TypedQuery<RestaurantType> query = em.createNamedQuery("researchAllRestaurantTypes",
                RestaurantType.class);
            return query.getResultStream().collect(Collectors.toSet());
        });
    }

    public Set<City> researchAllCities() {
        return inTransaction(em -> {
            TypedQuery<City> query = em.createNamedQuery("researchAllCities", City.class);
            return query.getResultStream().collect(Collectors.toSet());
        });
    }

    public void insertRestaurant(Restaurant restaurant) {
        inTransactionNoReturn(em -> em.persist(restaurant));
    }

    public void insertCity(City city) {
        inTransactionNoReturn(em -> em.persist(city));
    }

    public void insertBasicEvaluation(BasicEvaluation eval) {
        inTransactionNoReturn(em -> em.persist(eval));
    }

    public Set<EvaluationCriteria> researchAllEvaluationCriteria() {
        return inTransaction(em -> {
            TypedQuery<EvaluationCriteria> query = em.createNamedQuery(
                "researchAllEvaluationCriteria",
                EvaluationCriteria.class);
            return query.getResultStream().collect(Collectors.toSet());
        });
    }

    public void insertCompleteEvaluation(CompleteEvaluation eval) {
        inTransactionNoReturn(em -> em.persist(eval));
    }

    public void updateRestaurant(Restaurant restaurant) {
        inTransactionNoReturn(em -> em.merge(restaurant));
    }

    public void deleteRestaurant(Restaurant restaurant) {
        inTransactionNoReturn(em -> em.remove(restaurant));
    }

    public void inTransactionNoReturn(Consumer<EntityManager> function) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            function.accept(em);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public <T> T inTransaction(Function<EntityManager, T> function) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            T result = function.apply(em);
            em.getTransaction().commit();
            return result;
        } finally {
            em.close();
        }
    }

}
