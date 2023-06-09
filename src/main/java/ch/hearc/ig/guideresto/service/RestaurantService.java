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

import ch.hearc.ig.guideresto.persistence.*;
import ch.hearc.ig.guideresto.utils.RestaurantToRestaurantOverview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantService {

  private final Database database;
  private final RestaurantDAO restaurantDAO;
  private final RestaurantTypeDAO restaurantTypeDAO;
  private final CityDAO cityDAO;
  private final BasicEvaluationDAO basicEvaluationDAO;
  private final CompleteEvaluationDAO completeEvaluationDAO;
  private final EvaluationCriteriaDAO evaluationCriteriaDAO;

  public RestaurantService(Database database, RestaurantDAO restaurantDAO,
                           RestaurantTypeDAO restaurantTypeDAO, CityDAO cityDAO, BasicEvaluationDAO basicEvaluationDAO,
                           CompleteEvaluationDAO completeEValuationDAO, EvaluationCriteriaDAO evaluationCriteriaDAO) {
    this.database = database;
    this.restaurantDAO = restaurantDAO;
    this.restaurantTypeDAO = restaurantTypeDAO;
    this.cityDAO = cityDAO;
    this.basicEvaluationDAO = basicEvaluationDAO;
    this.completeEvaluationDAO = completeEValuationDAO;
    this.evaluationCriteriaDAO = evaluationCriteriaDAO;
  }

  public Set<RestaurantOverview> researchAllRestaurants() {
    return database.inTransaction(restaurantDAO::findAll);
  }

  public Restaurant researchRestaurantById(int restaurantId) {
    return database.inTransaction(() -> restaurantDAO.findById(restaurantId));
  }

  public Set<Restaurant> researchRestaurantsByName(String research) {
    return database.inTransaction(() -> restaurantDAO.findByName(research));
  }

  public Set<Restaurant> researchRestaurantsByCityName(String research) {
    return database.inTransaction(() -> restaurantDAO.findByCityName(research));
  }

  public Set<Restaurant> researchRestaurantsByType(RestaurantType restaurantType) {
    return database.inTransaction(() -> restaurantDAO.findByRestaurantType(restaurantType));
  }

  public Set<RestaurantType> researchAllRestaurantTypes() {
    return database.inTransaction(restaurantTypeDAO::findAll);
  }

  public Set<City> researchAllCities() {
    return database.inTransaction(cityDAO::findAll);
  }

  public void insertRestaurant(Restaurant restaurant) {
    database.inTransaction(() -> restaurantDAO.insert(restaurant));
  }

  public void insertCity(City city) {
    database.inTransaction(() -> cityDAO.insert(city));
  }

  public void insertBasicEvaluation(BasicEvaluation basicEvaluation) {
    database.inTransaction(() -> basicEvaluationDAO.insert(basicEvaluation));
  }

  public Set<EvaluationCriteria> researchAllEvaluationCriteria() {
    return database.inTransaction(evaluationCriteriaDAO::findAll);
  }

  public void insertCompleteEvaluation(CompleteEvaluation completeEvaluation) {
    database.inTransaction(() -> completeEvaluationDAO.insert(completeEvaluation));
  }

  public void updateRestaurant(Restaurant restaurant) {
    database.inTransaction(() -> restaurantDAO.update(restaurant));
  }

  public void deleteRestaurant(Restaurant restaurant) {
    database.inTransaction(() -> restaurantDAO.delete(restaurant));
  }

}
