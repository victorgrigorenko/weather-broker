package weather.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import weather.data.Location;

import java.util.List;

@Repository(value="locationDao")
class LocationDaoImpl extends AbstractDao<Location, String> {

    @Override
    @Transactional
    public Location get(String id) {
        Location location = sessionFactory.getCurrentSession().find(Location.class, id);
        return location;
    }

    @Override
    public List<Location> getAll() {
        return  (List<Location>) sessionFactory.getCurrentSession().createQuery("from Location").list();
    }
}
