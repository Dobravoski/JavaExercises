package LibraryExercise.domain.repositories;

import LibraryExercise.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, String> {
    Optional<User> findByID(String id);
}
