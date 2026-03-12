package LibraryExercise.infrastructure.repositories;

import LibraryExercise.domain.entities.User;

import java.util.*;

public class UserRepository implements LibraryExercise.domain.repositories.UserRepository {

    private final Map<String, User> users =  new HashMap<>();

    @Override
    public Optional<User> findByID(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void save(User entity) {
        users.put(entity.getID(), entity);
    }

    @Override
    public Set<User> findAll() {
        return Set.copyOf(users.values());
    }

    @Override
    public void delete(String id) {
        users.remove(id);
    }
}
