package utils;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantOverview;

public class RestaurantToRestaurantOverview {

    public static RestaurantOverview convert(Restaurant restaurant) {
        return new RestaurantOverview(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getStreet(),
                restaurant.getZipCode(),
                restaurant.getCityName()
        );
    }

}
