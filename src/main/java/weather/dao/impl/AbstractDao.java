package weather.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import weather.dao.AbstarctableDao;

import java.util.List;

@Repository
abstract class AbstractDao<T,Id> implements AbstarctableDao<T, Id> {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void saveToDatabase(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<T> locations = getAll();
        for (T entity : locations){
            delete(entity);
        }
    }

}
