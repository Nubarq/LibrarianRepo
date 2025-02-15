package az.developia.librarian.service.impl;

import az.developia.librarian.entity.Book;
import az.developia.librarian.entity.BorrowedBook;
import az.developia.librarian.entity.Student;
import az.developia.librarian.exeption.CustomException;
import az.developia.librarian.repository.BorrowedBookRepository;
import az.developia.librarian.repository.BookRepository;
import az.developia.librarian.repository.StudentRepository;
import az.developia.librarian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Give a book to a student
    public BorrowedBook giveBookToStudent(Integer bookId, Integer studentId) {
        // Check if the book exists
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + bookId));

        // Check if the student exists and is a student
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomException("Student not found with ID: " + studentId));

        // Check if the book is available (quantity > 0)
        if (book.getQuantity() <= 0) {
            throw new CustomException("Book is not available");
        }

        // Reduce the book quantity by 1
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // Create a new BorrowedBook record
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setBook(book);
        borrowedBook.setStudent(student);
        borrowedBook.setBorrowedDate(LocalDateTime.now());
        borrowedBook.setDueDate(LocalDateTime.now().plusDays(14)); // 14-day loan period
        borrowedBook.setReturnedDate(null);
        borrowedBook.setOverdue(false);

        // Save the BorrowedBook record
        return borrowedBookRepository.save(borrowedBook);
    }

//    // Get all currently borrowed books
//    public List<BorrowedBook> getCurrentlyBorrowedBooks() {
//        return borrowedBookRepository.findByReturnedDateIsNull();
//    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Return a book
    public BorrowedBook returnBook(Integer borrowedBookId) {
        // Check if the BorrowedBook record exists
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId)
                .orElseThrow(() -> new IllegalArgumentException("Borrowed book not found with ID: " + borrowedBookId));

        // Check if the book is already returned
        if (borrowedBook.getReturnedDate() != null) {
            throw new IllegalArgumentException("Book is already returned");
        }

        // Update the returned date
        borrowedBook.setReturnedDate(LocalDateTime.now());

        // Check if the book is overdue
        if (borrowedBook.getReturnedDate().isAfter(borrowedBook.getDueDate())) {
            borrowedBook.setOverdue(true);
        }

        // Increase the book quantity by 1
        Book book = borrowedBook.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        // Save the updated BorrowedBook record
        return borrowedBookRepository.save(borrowedBook);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    // Get all returned books
    public List<BorrowedBook> getReturnedBooks() {
        return borrowedBookRepository.findByReturnedDateIsNotNull();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    // Get all currently borrowed books (not yet returned)
    @Override
    public List<BorrowedBook> getCurrentlyBorrowedBooks() {
        // Fetch all borrowed books
        List<BorrowedBook> allBorrowedBooks = borrowedBookRepository.findAll();

        // Filter books where returnedDate is null
        return allBorrowedBooks.stream()
                .filter(bb -> bb.getReturnedDate() == null)
                .collect(Collectors.toList());
    }


    // Optional: Get all currently borrowed books by a specific student
    @Override
    public List<BorrowedBook> getCurrentlyBorrowedBooksByStudent(Long studentId) {
        // Fetch all borrowed books
        List<BorrowedBook> allBorrowedBooks = borrowedBookRepository.findAll();

        // Filter books where returnedDate is null and studentId matches
        return allBorrowedBooks.stream()
                .filter(bb -> bb.getReturnedDate() == null && bb.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
    }


    // Optional: Get all currently borrowed books of a specific book
    @Override
    public List<BorrowedBook> getCurrentlyBorrowedBooksByBook(Long bookId) {
        // Fetch all borrowed books
        List<BorrowedBook> allBorrowedBooks = borrowedBookRepository.findAll();

        // Filter books where returnedDate is null and bookId matches
        return allBorrowedBooks.stream()
                .filter(bb -> bb.getReturnedDate() == null && bb.getBook().getId().equals(bookId))
                .collect(Collectors.toList());
    }
}
