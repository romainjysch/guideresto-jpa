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
    List<Restaurant> restaurants = inTransactionFunction(em -> {
      TypedQuery<Restaurant> query = em.createNamedQuery("researchAllRestaurants", Restaurant.class);
      return query.getResultList();
    });
    Set<RestaurantOverview> restaurantOverviews = new HashSet<>();
    for (Restaurant restaurant : restaurants) {
      RestaurantOverview restaurantOverview = RestaurantToRestaurantOverview.convert(restaurant);
      restaurantOverviews.add(restaurantOverview);
    }
    return restaurantOverviews;
  }

  public Restaurant researchRestaurantById(int restaurantId) {
    return inTransactionFunction(em -> {
      TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantById", Restaurant.class)
              .setParameter(1, restaurantId);
      return query.getSingleResult();
    });
  }

  public Set<Restaurant> researchRestaurantsByName(String research) {
    return inTransactionFunction(em -> {
      TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByName", Restaurant.class)
              .setParameter(1, research + "%");
      return query.getResultStream().collect(Collectors.toSet());
    });
  }

  public Set<Restaurant> researchRestaurantsByCityName(String research) {
    return inTransactionFunction(em -> {
      TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByCityName", Restaurant.class)
              .setParameter(1, research + "%");
      return query.getResultStream().collect(Collectors.toSet());
    });
  }

  public Set<Restaurant> researchRestaurantsByType(RestaurantType restaurantType) {
    return inTransactionFunction(em -> {
      TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByType", Restaurant.class)
              .setParameter(1, restaurantType);
      return query.getResultStream().collect(Collectors.toSet());
    });
  }

  public Set<RestaurantType> researchAllRestaurantTypes() {
    return inTransactionFunction(em -> {
      TypedQuery<RestaurantType> query = em.createNamedQuery("researchAllRestaurantTypes", RestaurantType.class);
      return query.getResultStream().collect(Collectors.toSet());
    });
  }

  public Set<City> researchAllCities() {
    return inTransactionFunction(em -> {
      TypedQuery<City> query = em.createNamedQuery("researchAllCities", City.class);
      return query.getResultStream().collect(Collectors.toSet());
    });
  }

  public void insertRestaurant(Restaurant restaurant) {
    inTransactionConsumer(em -> em.persist(restaurant));
  }

  public void insertCity(City city) {
    inTransactionConsumer(em -> em.persist(city));
  }

  public void insertBasicEvaluation(BasicEvaluation eval) {
    inTransactionConsumer(em -> em.persist(eval));
  }

  public Set<EvaluationCriteria> researchAllEvaluationCriteria() {
    return inTransactionFunction(em -> {
      TypedQuery<EvaluationCriteria> query = em.createNamedQuery("researchAllEvaluationCriteria", EvaluationCriteria.class);
      return query.getResultStream().collect(Collectors.toSet());
    });
  }

  public void insertCompleteEvaluation(CompleteEvaluation eval) {
    inTransactionConsumer(em -> em.persist(eval));
  }

  public void updateRestaurant(Restaurant restaurant) {
    inTransactionConsumer(em -> em.merge(restaurant));
  }

  public void deleteRestaurant(Restaurant restaurant) {
    inTransactionConsumer(em -> em.remove(restaurant));
  }

  private <T> T inTransactionFunction(Function<EntityManager, T> function) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      T result = function.apply(em);
      em.getTransaction().commit();
      return result;
    } catch (Exception e) {
      em.getTransaction().rollback();
      e.printStackTrace();
      return null;
    } finally {
      //em.close();
    }
  }

  private void inTransactionConsumer(Consumer<EntityManager> consumer) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      consumer.accept(em);
      em.getTransaction().commit();
    } catch (Exception e) {
      em.getTransaction().rollback();
      e.printStackTrace();
    } finally {
      //em.close();
    }
  }

}
