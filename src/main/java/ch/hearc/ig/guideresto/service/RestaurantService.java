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
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchAllRestaurants", Restaurant.class);
    List<Restaurant> restaurants = dbTransaction.inTransaction(query::getResultList);
    Set<RestaurantOverview> restaurantOverviews = new HashSet<>();
    for (Restaurant restaurant : restaurants) {
      RestaurantOverview restaurantOverview = RestaurantToRestaurantOverview.convert(restaurant);
      restaurantOverviews.add(restaurantOverview);
    }
    return restaurantOverviews;
  }

  public Restaurant researchRestaurantById(int restaurantId) {
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchRestaurantById", Restaurant.class)
            .setParameter(1, restaurantId);
    return dbTransaction.inTransaction(query::getSingleResult);
  }

  public Set<Restaurant> researchRestaurantsByName(String research) {
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchRestaurantsByName", Restaurant.class)
            .setParameter(1, research + "%");
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public Set<Restaurant> researchRestaurantsByCityName(String research) {
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchRestaurantsByCityName", Restaurant.class)
            .setParameter(1, research + "%");
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public Set<Restaurant> researchRestaurantsByType(RestaurantType restaurantType) {
    TypedQuery<Restaurant> query = dbTransaction.getEm()
            .createNamedQuery("researchRestaurantsByType", Restaurant.class)
            .setParameter(1, restaurantType);
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public Set<RestaurantType> researchAllRestaurantTypes() {
    TypedQuery<RestaurantType> query = dbTransaction.getEm()
            .createNamedQuery("researchAllRestaurantTypes", RestaurantType.class);
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public Set<City> researchAllCities() {
    TypedQuery<City> query = dbTransaction.getEm()
            .createNamedQuery("researchAllCities", City.class);
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public void insertRestaurant(Restaurant restaurant) {
    dbTransaction.inTransaction(() -> dbTransaction.getEm().persist(restaurant));
  }

  public void insertCity(City city) {
    dbTransaction.inTransaction(() -> dbTransaction.getEm().persist(city));
  }

  public void insertBasicEvaluation(BasicEvaluation eval) {
    dbTransaction.inTransaction(() -> dbTransaction.getEm().persist(eval));
  }

  public Set<EvaluationCriteria> researchAllEvaluationCriteria() {
    TypedQuery<EvaluationCriteria> query = dbTransaction.getEm()
            .createNamedQuery("researchAllEvaluationCriteria", EvaluationCriteria.class);
    return dbTransaction.inTransaction(() -> query.getResultStream().collect(Collectors.toSet()));
  }

  public void insertCompleteEvaluation(CompleteEvaluation eval) {
    dbTransaction.inTransaction(() -> dbTransaction.getEm().persist(eval));
  }

  public void updateRestaurant(Restaurant restaurant) {
    dbTransaction.inTransaction(() -> dbTransaction.getEm().persist(restaurant));
  }

  public void deleteRestaurant(Restaurant restaurant) {
    dbTransaction.inTransaction(() -> dbTransaction.getEm().remove(restaurant));
  }
}
