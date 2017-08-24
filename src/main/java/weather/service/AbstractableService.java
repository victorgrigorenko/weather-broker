package weather.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AbstractableService<T, Id> { // Location

    void saveToDatabase(T entity);
    T get(Id id);
    void update(T entity);
    void delete(T entity);

    List<T> getAll();
    void deleteAll();
}

