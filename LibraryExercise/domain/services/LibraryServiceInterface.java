package LibraryExercise.domain.services;

import LibraryExercise.domain.entities.Book;
import LibraryExercise.domain.entities.Loan;
import LibraryExercise.domain.entities.User;

import java.util.Set;

public interface LibraryServiceInterface {
    void registerBook(Book book);
    void registerUser(User user);
    void loanBook(String isbn, String userId);
    void returnBook(String isbn, String userId);
    Set<Book> listAvailableBooks();
    Set<Loan> listLoans();
}
