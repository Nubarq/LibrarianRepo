package az.developia.librarian.service;

import az.developia.librarian.entity.BorrowedBook;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    // Get all currently borrowed books (not yet returned)
    public List<BorrowedBook> getCurrentlyBorrowedBooks() ;

    // Optional: Get all currently borrowed books by a specific student
    public List<BorrowedBook> getCurrentlyBorrowedBooksByStudent(Long studentId);

    // Optional: Get all currently borrowed books of a specific book
    public List<BorrowedBook> getCurrentlyBorrowedBooksByBook(Long bookId);

    ////////////////////////////////////////////////////////////

    BorrowedBook giveBookToStudent(Integer bookId, Integer studentId);
    BorrowedBook returnBook(Integer borrowedBookId);

    List<BorrowedBook> getReturnedBooks();
}
