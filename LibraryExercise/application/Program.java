package LibraryExercise.application;

import LibraryExercise.domain.entities.Book;
import LibraryExercise.domain.entities.Loan;
import LibraryExercise.domain.entities.User;
import LibraryExercise.domain.services.LibraryService;
import LibraryExercise.infrastructure.repositories.BookRepository;
import LibraryExercise.infrastructure.repositories.UserRepository;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {
    static void main() {
        library();
    }

    public static void library() {

        final Scanner sc = new Scanner(System.in);
        final LibraryService libraryService = new LibraryService(new UserRepository(), new BookRepository());

        outer: while (true) {

            System.out.println("""
                    
                    ================================
                            Library System
                    ================================
                    
                    1 - Register new user
                    2 - Add new book
                    3 - List all books
                    4 - List all available books
                    5 - Borrow book
                    6 - Return book
                    7 - List active loans
                    0 - Exit""");

            String isbn;
            String userID;
            switch (readInt(0, 7, sc)) {
                case 1:
                    System.out.println("\n===== Register user =====\n");
                    System.out.print("Enter user ID: ");
                    userID = sc.nextLine();
                    System.out.print("Enter user name: ");
                    String userName = sc.nextLine();
                    libraryService.registerUser(new User(userID, userName));
                    System.out.println("User successfully registered.");
                    break;
                case 2:
                    System.out.println("\n===== Add book =====\n");
                    System.out.print("Enter ISBN: ");
                    isbn = sc.nextLine();
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter book author: ");
                    String author = sc.nextLine();
                    System.out.println("Enter book release year");
                    int year = readInt(-1000, 2026, sc);
                    libraryService.registerBook(new Book(isbn, title, author, year));
                    System.out.println("Book successfully registered.");
                    break;
                case 3:
                    if (!libraryService.listAllBooks().isEmpty()) {
                        System.out.println("\n===== All books =====\n");
                        libraryService.listAllBooks().forEach(System.out::println);
                    } else {
                        System.out.println("\n===== No registered book =====\n");
                    }
                    break;
                case 4:
                    if (!libraryService.listAvailableBooks().isEmpty()) {
                        System.out.println("\n===== All available books =====\n");
                        libraryService.listAvailableBooks().forEach(System.out::println);
                    } else {
                        System.out.println("\n===== No available books =====\n");
                    }
                    break;
                case 5:
                    if (!libraryService.listAllBooks().isEmpty()) {
                        System.out.println("\n===== Borrow book =====\n");
                        userID = readID(libraryService, sc);
                        isbn = readISBN(libraryService, sc);
                        libraryService.loanBook(isbn, userID);
                        System.out.println("Book borrowed successfully.");
                        libraryService.getLoanDate(isbn, userID).ifPresent(date -> System.out.println("Loan date: " + date));
                        libraryService.getLoanDueDate(isbn, userID).ifPresent(date -> System.out.println("Loan due date: " + date));
                    } else {
                        System.out.println("\n===== There are no books to borrow =====\n");
                    }
                    break;
                case 6:
                    if (!libraryService.listLoans().isEmpty()) {
                        System.out.println("\n===== Return book =====\n");
                        userID = readID(libraryService, sc);
                        isbn = readISBN(libraryService, sc);
                        libraryService.returnBook(isbn, userID);
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("\n===== There's no book to return =====\n");
                    }
                    break;
                case 7:
                    if (!libraryService.listLoans().isEmpty()) {
                        System.out.println("\n===== Active loans =====\n");
                        for (Loan loan : libraryService.listLoans()) {
                            System.out.println("User: " + loan.getUser().getUsername() + " (" + loan.getUser().getID() + ")");
                            System.out.println("Book: " + loan.getBook().getTitle());
                            System.out.println("Book author: " + loan.getBook().getAuthor());
                            System.out.println("Loan date: " + loan.getLoanDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            System.out.println("Due date: " + loan.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            System.out.println("\n-----------------------------\n");
                        }
                    } else {
                        System.out.println("\n===== No active loans =====\n");
                    }
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break outer;
            }
        }
    }

    public static int readInt(int start, int end, Scanner sc) {
        while (true) {
            try {
                System.out.print(">> ");
                int answer = Integer.parseInt(sc.nextLine());
                if (answer < start || answer > end) {
                    System.out.println("The input must be between " + start + " and " + end);
                    continue;
                }
                return answer;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
        }
    }

    public static String readISBN(LibraryService libraryService, Scanner sc) {
        System.out.println("ISBN: ");
        System.out.print(">> ");
        String isbn = sc.nextLine();
        while(!libraryService.bookExists(isbn)) {
            System.out.println("Invalid ISBN");
            System.out.print(">> ");
            isbn = sc.nextLine();
        }
        return isbn;
    }

    public static String readID(LibraryService libraryService, Scanner sc) {
        System.out.println("ID: ");
        System.out.print(">> ");
        String userID = sc.nextLine();
        while(!libraryService.userExists(userID)) {
            System.out.println("Invalid user ID");
            System.out.print(">> ");
            userID = sc.nextLine();
        }
        return userID;
    }
}
