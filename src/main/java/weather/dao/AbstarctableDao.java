package weather.dao;

import java.util.List;

public interface AbstarctableDao<T, Id> {

    void saveToDatabase(T entity);
    T get(Id id);
    void update(T entity);
    void delete(T entity);

    List<T> getAll();
    void deleteAll();
}
