package LibraryExercise.domain.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private final Book book;
    private final User user;
    private final LocalDate loanDate;

    public Loan(Book book, User user) {
        this.book = book;
        this.user = user;
        loanDate = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(book, loan.book) && Objects.equals(user, loan.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, user);
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getLoanDate() {return loanDate;}

    public LocalDate getDueDate() {return loanDate.plusDays(7);}
}
