import java.util.*;

// --------- Book Class ----------
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        return id + " - " + title + " by " + author + (isAvailable ? " [Available]" : " [Issued]");
    }
}

// --------- User Class ----------
class User {
    private int id;
    private String name;
    private List<Book> issuedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void issueBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            issuedBooks.add(book);
            System.out.println(name + " issued: " + book.getTitle());
        } else {
            System.out.println("Book already issued.");
        }
    }

    public void returnBook(Book book) {
        if (issuedBooks.remove(book)) {
            book.setAvailable(true);
            System.out.println(name + " returned: " + book.getTitle());
        } else {
            System.out.println("Book not issued to user.");
        }
    }
}

// --------- Library Class ----------
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        if (book != null && user != null) {
            user.issueBook(book);
        } else {
            System.out.println("Invalid book or user ID.");
        }
    }

    public void returnBook(int bookId, int userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        if (book != null && user != null) {
            user.returnBook(book);
        } else {
            System.out.println("Invalid book or user ID.");
        }
    }

    public void showAllBooks() {
        System.out.println("Books in Library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

// --------- Main Class ----------
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Add Books
        library.addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(2, "Atomic Habits", "James Clear"));
        library.addBook(new Book(3, "1984", "George Orwell"));

        // Register Users
        library.registerUser(new User(1, "Alice"));
        library.registerUser(new User(2, "Bob"));

        // Operations
        library.showAllBooks();
        library.issueBook(1, 1);  // Alice issues The Alchemist
        library.issueBook(2, 2);  // Bob issues Atomic Habits
        library.issueBook(1, 2);  // Try to issue already issued book
        library.showAllBooks();
        library.returnBook(1, 1); // Alice returns The Alchemist
        library.showAllBooks();
    }
}