package ch.hearc.ig.guideresto.persistence;

import static ch.hearc.ig.guideresto.persistence.Database.getEntityManager;

import ch.hearc.ig.guideresto.business.City;

import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

public class CityDAO {

    public Set<City> findAll() {
        TypedQuery<City> query = getEntityManager().createNamedQuery("researchAllCities", City.class);
        return query.getResultStream().collect(Collectors.toSet());
    }

    public void insert(City city) {
        getEntityManager().persist(city);
    }

}
