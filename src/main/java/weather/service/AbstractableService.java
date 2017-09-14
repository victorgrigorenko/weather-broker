package weather.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AbstractableService<T, Id> {

    void saveToDatabase(T entity);
    T get(Id id);

    List<T> getAll();
}

