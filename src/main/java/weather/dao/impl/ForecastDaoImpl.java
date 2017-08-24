package weather.dao.impl;

import org.springframework.stereotype.Repository;
import weather.data.Forecast;

import java.util.List;

@Repository(value="forecastDao")
class ForecastDaoImpl extends AbstractDao<Forecast,Integer> {

    @Override
    public Forecast get(Integer id) {
        Forecast forecast = sessionFactory.getCurrentSession().get(Forecast.class, id);
        return forecast;
    }

    @Override
    public List<Forecast> getAll() {
        return  (List<Forecast>) sessionFactory.getCurrentSession().createQuery("from Forecast").list();
    }
}
