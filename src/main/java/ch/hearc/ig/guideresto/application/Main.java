package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.persistence.*;
import ch.hearc.ig.guideresto.presentation.CLI;
import ch.hearc.ig.guideresto.service.RestaurantService;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    cli();
  }

  private static void cli() {
    try (var database = Database.getInstance()) {
      var scanner = new Scanner(System.in);
      var printStream = System.out;
      var restaurantService = new RestaurantService(
              database,
              RestaurantDAO.getInstance(),
              RestaurantTypeDAO.getInstance(),
              CityDAO.getInstance(),
              BasicEvaluationDAO.getInstance(),
              CompleteEvaluationDAO.getInstance(),
              EvaluationCriteriaDAO.getInstance());
      var cli = CLI.getInstance(scanner, printStream, restaurantService);
      cli.start();
    }
  }

}
