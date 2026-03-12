package LibraryExercise.domain.repositories;

import java.util.Set;

public interface Repository<T, ID> {
    void save(T entity);
    Set<T> findAll();
    void delete(ID ID);
}
