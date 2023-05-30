package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.business.*;
import ch.hearc.ig.guideresto.presentation.CLI;
import ch.hearc.ig.guideresto.service.RestaurantService;
import utils.RestaurantToRestaurantOverview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    //jpql();
    cli();
    //show();
  }

  private static void jpql() {
    var entityManagerFactory = Persistence.createEntityManagerFactory("guideRestoPersistenceUnit");
    var restaurantService = new RestaurantService(entityManagerFactory);
    Set<RestaurantOverview> restaurantOverviews = restaurantService.researchAllRestaurants();
    for (RestaurantOverview restaurantOverview : restaurantOverviews) {
      System.out.println(restaurantOverview);
    }
  }

  private static void cli() {
    var scanner = new Scanner(System.in);
    var printStream = System.out;
    var entityManagerFactory = Persistence.createEntityManagerFactory("guideRestoPersistenceUnit");
    var restaurantService = new RestaurantService(entityManagerFactory);
    var cli = new CLI(scanner, printStream, restaurantService);
    cli.start();
  }

  private static void show() {
    // EntityManagerFactory = retourne un DataPool Oracle
    // EntityManager = retourne une Connection
    // Deux manières de créer un EntityManager
    // Le paramètre match avec le PersistanceUnit dans le fichier .xml QUE CA POUR CE COURS
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("guideRestoPersistenceUnit");
    EntityManager em = null;
    try {
      em = emf.createEntityManager(); // Crée la Connection
      em.getTransaction().begin();

      // Show City :
      City city = em.find(City.class, 1);
      //city.setCityName("Chichville");
      System.out.println(city);
      System.out.println();

      // Show RestaurantType :
      RestaurantType restaurantType = em.find(RestaurantType.class, 1);
      System.out.println(restaurantType);
      System.out.println();

      // Show restaurant :
      Restaurant retrievedRestaurant = em.find(Restaurant.class, 2);
      //retrievedRestaurant.setType(restaurantType);
      RestaurantOverview restaurantOverview = new RestaurantOverview(
              retrievedRestaurant.getId(),
              retrievedRestaurant.getName(),
              retrievedRestaurant.getStreet(),
              retrievedRestaurant.getZipCode(),
              retrievedRestaurant.getCityName());
      System.out.println(restaurantOverview);
      System.out.println();

      // Show BasicEvaluation :
      BasicEvaluation like = em.find(BasicEvaluation.class, 8);
      //like.setLikeRestaurant(false);
      //em.persist(like);
      System.out.println(like);
      System.out.println();

      // Show CompleteEvaluation :
      CompleteEvaluation completeEvaluation = em.find(CompleteEvaluation.class,3);
      System.out.println(completeEvaluation);
      System.out.println();

      // Show EvaluationCriteria :
      EvaluationCriteria evaluationCriteria = em.find(EvaluationCriteria.class, 2);
      System.out.println(evaluationCriteria);
      System.out.println();

      // Show Grade :
      Grade grade = em.find(Grade.class, 8);
      System.out.println(grade);
      System.out.println();

      em.getTransaction().commit();
    } catch (Exception e) {
      if(em!=null) {
        em.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if(em!=null) {
        em.close();
      }
      emf.close();
    }
  }

}
