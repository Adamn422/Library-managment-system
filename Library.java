import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.*;

public class Library implements Serializable {
    private List<Book> books;
    private List<Borrower> borrowers;
    private List<Transaction> transactions;

    public Library() {
        books = new ArrayList<>();
        borrowers = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<Borrower> borrowers) {
        this.borrowers = borrowers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                if (!book.isAvailable()) {
                    System.out.println("Book is currently borrowed and cannot be removed.");
                    return false;
                }
                books.remove(book);
                return true;
            }
        }
        System.out.println("Book not found in library.");
        return false;
    }

    public void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    public boolean removeBorrower(String borrowerId) {
        for (Borrower borrower : borrowers) {
            if (borrower.getBorrowerId().equals(borrowerId)) {
                borrowers.remove(borrower);
                return true;
            }
        }
        System.out.println("Borrower not found in library.");
        return false;
    }

    public void checkoutBook(String ISBN, String borrowerId) {
        Book bookToCheckout = null;
        Borrower borrower = null;

        // Find the book by its ISBN
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                bookToCheckout = book;
                break;
            }
        }

        // Find the borrower by their ID
        for (Borrower b : borrowers) {
            if (b.getBorrowerId().equals(borrowerId)) {
                borrower = b;
                break;
            }
        }

        // If we found the book and the borrower, and the book is available, then we create a transaction
        if (bookToCheckout != null && borrower != null) {
            if (bookToCheckout.isAvailable()) {
                Transaction transaction = new Transaction(bookToCheckout, borrower, LocalDate.now(), LocalDate.now().plusDays(14)); // assuming a 2-week loan period
                transactions.add(transaction);
                bookToCheckout.setAvailable(false); // mark the book as unavailable
            } else {
                System.out.println("Book is currently unavailable.");
            }
        } else {
            System.out.println("Book or borrower not found.");
        }
    }

    public void returnBook(String ISBN) {
        Transaction transactionToReturn = null;

        // Find the transaction by the book's ISBN
        for (Transaction transaction : transactions) {
            if (transaction.getBook().getISBN().equals(ISBN)) {
                transactionToReturn = transaction;
                break;
            }
        }

        // If we found the transaction, then we remove it and mark the book as available
        if (transactionToReturn != null) {
            transactions.remove(transactionToReturn);
            transactionToReturn.getBook().setAvailable(true);
        } else {
            System.out.println("Transaction not found.");
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static Library loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Library) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return null;
        }
    }
}


