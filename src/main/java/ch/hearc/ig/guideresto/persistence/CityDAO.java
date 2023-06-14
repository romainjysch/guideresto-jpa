package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.City;

import java.util.Set;
import java.util.stream.Collectors;

public class CityDAO {

    private static CityDAO instance;

    private CityDAO() {}

    public static CityDAO getInstance() {
        if (instance == null) {
            instance = new CityDAO();
        }
        return instance;
    }

    public Set<City> findAll() {
        return getEntityManager().createNamedQuery("researchAllCities", City.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    public void insert(City city) {
        getEntityManager().persist(city);
    }

}
