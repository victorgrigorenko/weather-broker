package weather.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import weather.dao.AbstarctableDao;
import weather.service.AbstractableService;

import java.util.List;


abstract class AbstractService<T,Id> implements AbstractableService<T,Id>{

    @Autowired
    protected AbstarctableDao<T,Id> abstractDao;

    @Transactional
    public void saveToDatabase(T entity){
        abstractDao.saveToDatabase(entity);
    }

    @Transactional
    public T get(Id id){
        T instance = abstractDao.get(id);
        return  instance;
    }


    @Transactional
    public void delete(T entity){
        abstractDao.delete(entity);
    }

    @Transactional
    public List<T> getAll(){
        List<T> list = abstractDao.getAll();
        return  list;
    }

    @Transactional
    public void deleteAll(){
        abstractDao.deleteAll();
    }

}
