package weather.dao;

import java.util.List;

public interface AbstarctableDao<T, Id> {

    void saveToDatabase(T entity);
    T get(Id id_location);
    void delete(T entity);

    List<T> getAll();
    void deleteAll();
}
