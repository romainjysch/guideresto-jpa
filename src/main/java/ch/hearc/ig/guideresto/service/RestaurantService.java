package ch.hearc.ig.guideresto.service;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantOverview;
import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.DBTransaction;
import utils.RestaurantToRestaurantOverview;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantService {

  private final DBTransaction dbTransaction;

  public RestaurantService(DBTransaction dbTransaction) {
    this.dbTransaction = dbTransaction;
  }

  public Set<RestaurantOverview> researchAllRestaurants() {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchAllRestaurants", Restaurant.class);
    List<Restaurant> restaurants = dbTransaction.inTransaction(query::getResultList);
    Set<RestaurantOverview> restaurantOverviews = new HashSet<>();
    for (Restaurant restaurant : restaurants) {
      RestaurantOverview restaurantOverview = RestaurantToRestaurantOverview.convert(restaurant);
      restaurantOverviews.add(restaurantOverview);
    }
    return restaurantOverviews;
  }

  public Restaurant researchRestaurantById(int restaurantId) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantById", Restaurant.class)
            .setParameter(1, restaurantId);
    return dbTransaction.inTransaction(query::getSingleResult);
  }

  public Set<Restaurant> researchRestaurantsByName(String research) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByName", Restaurant.class)
            .setParameter(1, research + "%");
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public Set<Restaurant> researchRestaurantsByCityName(String research) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByCityName", Restaurant.class)
            .setParameter(1, research + "%");
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public Set<Restaurant> researchRestaurantsByType(RestaurantType restaurantType) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    TypedQuery<Restaurant> query = em.createNamedQuery("researchRestaurantsByType", Restaurant.class)
            .setParameter(1, restaurantType);
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public Set<RestaurantType> researchAllRestaurantTypes() {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    TypedQuery<RestaurantType> query = em.createNamedQuery("researchAllRestaurantTypes", RestaurantType.class);
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public Set<City> researchAllCities() {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    TypedQuery<City> query = em.createNamedQuery("researchAllCities", City.class);
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public void insertRestaurant(Restaurant restaurant) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    dbTransaction.inTransaction(() -> em.persist(restaurant));
    em.getTransaction().commit();
  }

  public void insertCity(City city) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    dbTransaction.inTransaction(() -> em.persist(city));
    em.getTransaction().commit();
  }

  public void insertBasicEvaluation(BasicEvaluation eval) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    dbTransaction.inTransaction(() -> em.persist(eval));
    em.getTransaction().commit();
  }

  public Set<EvaluationCriteria> researchAllEvaluationCriteria() {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    TypedQuery<EvaluationCriteria> query = em.createNamedQuery("researchAllEvaluationCriteria", EvaluationCriteria.class);
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public void insertCompleteEvaluation(CompleteEvaluation eval) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    dbTransaction.inTransaction(() -> em.persist(eval));
    em.getTransaction().commit();
  }

  public void updateRestaurant(Restaurant restaurant) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    dbTransaction.inTransaction(() -> em.persist(restaurant));
    em.getTransaction().commit();
  }

  public void deleteRestaurant(Restaurant restaurant) {
    EntityManager em = dbTransaction.getEmf().createEntityManager();
    em.getTransaction().begin();
    dbTransaction.inTransaction(() -> em.remove(restaurant));
    em.getTransaction().commit();
  }
}
