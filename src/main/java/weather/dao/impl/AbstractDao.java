package weather.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import weather.dao.AbstarctableDao;


@Repository
abstract class AbstractDao<T,Id> implements AbstarctableDao<T, Id> {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void saveToDatabase(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

}
