package LibraryExercise.domain.repositories;

import LibraryExercise.domain.entities.Book;

import java.util.Optional;

public interface BookRepository extends Repository<Book, String> {
    Optional<Book> findByISBN(String isbn);
}
