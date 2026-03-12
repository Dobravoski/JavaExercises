package LibraryExercise.infrastructure.repositories;

import LibraryExercise.domain.entities.Book;

import java.util.*;

public class BookRepository implements LibraryExercise.domain.repositories.BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    @Override
    public Optional<Book> findByISBN(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public void save(Book entity) {
        books.put(entity.getIsbn(), entity);
    }

    @Override
    public Set<Book> findAll() {
        return Set.copyOf(books.values());
    }

    @Override
    public void delete(String isbn) {
        books.remove(isbn);
    }
}
