package LibraryExercise.domain.entities;

import java.util.Comparator;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;

    public Book(String isbn, String title, String author,  int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {return author;}
    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    @Override
    public int compareTo(Book o) {
        return Comparator.comparing(Book::getTitle, Comparator.nullsLast(String::compareTo)).compare(this, o);
    }

    @Override
    public String toString() {
        return """
                ISBN: %s
                Title: %s
                Author: %s
                Year: %d
                """.formatted(getIsbn(), getTitle(), getAuthor(), getYear());
    }
}
