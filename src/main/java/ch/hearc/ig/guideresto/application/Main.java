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
    var scanner = new Scanner(System.in);
    var printStream = System.out;
    var database = new Database();
    var restaurantService = new RestaurantService(
            database,
            new RestaurantDAO(),
            new RestaurantTypeDAO(),
            new CityDAO(),
            new BasicEvaluationDAO(),
            new CompleteEvaluationDAO(),
            new EvaluationCriteriaDAO());
    var cli = new CLI(scanner, printStream, restaurantService);
    cli.start();
  }

}
