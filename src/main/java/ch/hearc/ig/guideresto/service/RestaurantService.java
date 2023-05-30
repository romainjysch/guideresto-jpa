package ch.hearc.ig.guideresto.service;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantOverview;
import ch.hearc.ig.guideresto.business.RestaurantType;
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
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchAllRestaurants", Restaurant.class);
    List<Restaurant> restaurants = query.getResultList();
    Set<RestaurantOverview> restaurantOverviews = new HashSet<>();
    for (Restaurant restaurant : restaurants) {
      RestaurantOverview restaurantOverview = RestaurantToRestaurantOverview.convert(restaurant);
      restaurantOverviews.add(restaurantOverview);
    }
    return restaurantOverviews;
  }

  public Restaurant researchRestaurantById(int restaurantId) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantById", Restaurant.class)
            .setParameter(1, restaurantId);
    return query.getSingleResult();
  }

  public Set<Restaurant> researchRestaurantsByName(String research) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByName", Restaurant.class)
            .setParameter(1, research + "%");
    return query.getResultStream().collect(Collectors.toSet());
  }

  public Set<Restaurant> researchRestaurantsByCityName(String research) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByCityName", Restaurant.class)
            .setParameter(1, research + "%");
    return query.getResultStream().collect(Collectors.toSet());
  }

  public Set<Restaurant> researchRestaurantsByType(RestaurantType restaurantType) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByType", Restaurant.class)
            .setParameter(1, restaurantType);
    return query.getResultStream().collect(Collectors.toSet());
  }

  public Set<RestaurantType> researchAllRestaurantTypes() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    TypedQuery<RestaurantType> query = em.createNamedQuery("researchAllRestaurantTypes", RestaurantType.class);
    return query.getResultStream().collect(Collectors.toSet());
  }

  public Set<City> researchAllCities() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    TypedQuery<City> query = em.createNamedQuery("researchAllCities", City.class);
    return query.getResultStream().collect(Collectors.toSet());
  }

  public void insertRestaurant(Restaurant restaurant) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(restaurant);
    em.getTransaction().commit();
  }

  public void insertCity(City city) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(city);
    em.getTransaction().commit();
  }

  public void insertBasicEvaluation(BasicEvaluation eval) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(eval);
    em.getTransaction().commit();
  }

  public Set<EvaluationCriteria> researchAllEvaluationCriteria() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    TypedQuery<EvaluationCriteria> query = em.createNamedQuery("researchAllEvaluationCriteria", EvaluationCriteria.class);
    return query.getResultStream().collect(Collectors.toSet());
  }

  public void insertCompleteEvaluation(CompleteEvaluation eval) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(eval);
    em.getTransaction().commit();
  }

  public void updateRestaurant(Restaurant restaurant) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.merge(restaurant);
    em.getTransaction().commit();
  }

  public void deleteRestaurant(Restaurant restaurant) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.remove(restaurant);
    em.getTransaction().commit();
  }

}
