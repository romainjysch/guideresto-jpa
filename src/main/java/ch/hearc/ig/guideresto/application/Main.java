package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.presentation.CLI;
import ch.hearc.ig.guideresto.service.RestaurantService;

import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    cli();
  }

  private static void cli() {
    var scanner = new Scanner(System.in);
    var printStream = System.out;
    var entityManagerFactory = Persistence.createEntityManagerFactory("guideRestoPersistenceUnit");
    var restaurantService = new RestaurantService(entityManagerFactory);
    var cli = new CLI(scanner, printStream, restaurantService);
    cli.start();
  }

}
