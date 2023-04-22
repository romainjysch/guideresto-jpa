package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.business.Evaluation;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantOverview;
import ch.hearc.ig.guideresto.presentation.CLI;
import ch.hearc.ig.guideresto.service.RestaurantService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //cli();
    show();
  }

  private static void cli() {
    var scanner = new Scanner(System.in);
    var printStream = System.out;
    var restaurantService = new RestaurantService();
    var cli = new CLI(scanner, printStream, restaurantService);
    cli.start();
  }

  private static void show() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("guideRestoPersistenceUnit");
    EntityManager em = null;
    try {
      em = emf.createEntityManager();
      em.getTransaction().begin();
      // Show restaurant :
      Restaurant retrievedRestaurant = em.find(Restaurant.class, 2);
      RestaurantOverview restaurantOverview = new RestaurantOverview(
              retrievedRestaurant.getId(),
              retrievedRestaurant.getName(),
              retrievedRestaurant.getStreet(),
              retrievedRestaurant.getZipCode(),
              retrievedRestaurant.getCityName());
      System.out.println(restaurantOverview.toString());
      // Show like :
      BasicEvaluation like = em.find(BasicEvaluation.class, 8);
      System.out.println(like);
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
