package az.developia.librarian.controller;

import az.developia.librarian.entity.BorrowedBook;
import az.developia.librarian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowed-books")
public class BorrowedBookController {

    @Autowired
    private OrderService orderService;



    // Get all currently borrowed books
//    @GetMapping("/currently-borrowed")
//    public List<BorrowedBook> getCurrentlyBorrowedBooks() {
//        return orderService.getCurrentlyBorrowedBooks();
//    }

    // Optional: Get all currently borrowed books by a specific student
    @GetMapping("/currently-borrowed/by-student")
    @PreAuthorize("hasAuthority('GET_BORROWED_BOOKS_BY_STUDENT')")
    public List<BorrowedBook> getCurrentlyBorrowedBooksByStudent(@RequestParam Long studentId) {
        return orderService.getCurrentlyBorrowedBooksByStudent(studentId);
    }

    // Optional: Get all currently borrowed books of a specific book
    @GetMapping("/currently-borrowed/by-book")
    @PreAuthorize("hasAuthority('GET_BORROWED_BOOKS_BY_BOOK')")
    public List<BorrowedBook> getCurrentlyBorrowedBooksByBook(@RequestParam Long bookId) {
        return orderService.getCurrentlyBorrowedBooksByBook(bookId);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    // Give a book to a student
    @PostMapping("/give")
    @PreAuthorize("hasAuthority('GIVE_BOOK_TO_STUDENT')")
    public BorrowedBook giveBookToStudent(
            @RequestParam Integer bookId,
            @RequestParam Integer studentId) {
        return orderService.giveBookToStudent(bookId, studentId);
    }

    // See currently borrowed books
    @GetMapping("/currently-borrowed")
    @PreAuthorize("hasAuthority('GET_BORROWED_BOOKS')")
    public List<BorrowedBook> getCurrentlyBorrowedBooks() {
        return orderService.getCurrentlyBorrowedBooks();
    }

    // Return a book
    @PostMapping("/return/{borrowedBookId}")
    @PreAuthorize("hasAuthority('RETURN_BOOK')")
    public BorrowedBook returnBook(@PathVariable Integer borrowedBookId) {
        return orderService.returnBook(borrowedBookId);
    }

    // See returned books
    @GetMapping("/returned")
    @PreAuthorize("hasAuthority('GET_RETURNED_BOOKS')")
    public List<BorrowedBook> getReturnedBooks() {
        return orderService.getReturnedBooks();
    }


}
