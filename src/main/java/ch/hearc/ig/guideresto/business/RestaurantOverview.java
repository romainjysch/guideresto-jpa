package ch.hearc.ig.guideresto.business;

import lombok.Getter;

@Getter
public class RestaurantOverview implements IAmRestaurant {
  private final Integer id;
  private final String name;
  private final String street;
  private final String zipCode;
  private final String cityName;

  public RestaurantOverview(Integer id, String name, String street, String zipCode, String cityName) {
    this.id = id;
    this.name = name;
    this.street = street;
    this.cityName = cityName;
    this.zipCode = zipCode;
  }

  public String getName() {
    return name;
  }

  public String getStreet() {
    return street;
  }

  @Override
  public String getCityName() {
    return cityName;
  }

  public String getZipCode() {
    return zipCode;
  }

  @Override
  public String toString() {
    return "RestaurantOverview{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", street='" + street + '\'' +
            ", zipCode='" + zipCode + '\'' +
            ", cityName='" + cityName + '\'' +
            '}';
  }

}