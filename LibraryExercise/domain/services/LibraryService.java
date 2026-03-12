package LibraryExercise.domain.services;

import LibraryExercise.domain.entities.Book;
import LibraryExercise.domain.entities.Loan;
import LibraryExercise.domain.entities.User;
import LibraryExercise.infrastructure.repositories.BookRepository;
import LibraryExercise.infrastructure.repositories.UserRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryService implements LibraryServiceInterface {

    UserRepository userRepository;
    BookRepository bookRepository;
    Set<Loan> loans = new HashSet<>();

    public LibraryService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void registerBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void loanBook(String isbn, String userId) {
        try {
            int sizeBefore = loans.size();
            if (isbnIdIsNotEmpty(isbn, userId)) {
                loans.add(new Loan(bookRepository.findByISBN(isbn).get(), userRepository.findByID(userId).get()));
            }
            if (loans.size() == sizeBefore) {
                throw new LibraryException("This user has already borrowed this book.");
            }
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void returnBook(String isbn, String userId) {
        try {
            if (isbnIdIsNotEmpty(isbn, userId) && !loans.removeIf(loan -> loan.getBook().getIsbn().equals(isbn) && loan.getUser().getID().equals(userId))) {
                throw new LibraryException("Loan not found");
            }
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Set<Book> listAvailableBooks() {
        return bookRepository.findAll().stream().filter(book -> loans.stream().noneMatch(loan -> loan.getBook().equals(book))).collect(Collectors.toSet());
    }

    @Override
    public Set<Loan> listLoans() {
        return Set.copyOf(loans);
    }

    private boolean isbnIdIsNotEmpty(String isbn, String userId) throws LibraryException {
        if (bookRepository.findByISBN(isbn).isEmpty()) {
            throw new LibraryException("ISBN:" + isbn + " not found");
        } else if (userRepository.findByID(userId).isEmpty()) {
            throw new LibraryException("ID:" + userId + " not found");
        }
        return true;
    }

    public Set<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public boolean userExists(String userId) {
        return userRepository.findByID(userId).isPresent();
    }

    public boolean bookExists(String isbn) {
        return bookRepository.findByISBN(isbn).isPresent();
    }

    private Optional<Loan> getLoan(String isbn, String userId) {
        return loans.stream().filter(loan -> loan.getBook().getIsbn().equals(isbn) && loan.getUser().getID().equals(userId)).findFirst();
    }

    public Optional<LocalDate> getLoanDate(String isbn,  String userId) {
        return getLoan(isbn, userId).map(Loan::getLoanDate);
    }

    public Optional<LocalDate> getLoanDueDate(String isbn,  String userId) {
        return getLoan(isbn, userId).map(Loan::getDueDate);
    }
}
