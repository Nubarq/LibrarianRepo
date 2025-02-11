package az.developia.librarian.controller;

import az.developia.librarian.entity.BorrowedBook;
import az.developia.librarian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/borrowed-books")
public class BorrowedBookController {

    @Autowired
    private OrderService orderService;

    // Get all currently borrowed books
    @GetMapping("/currently-borrowed")
    public List<BorrowedBook> getCurrentlyBorrowedBooks() {
        return orderService.getCurrentlyBorrowedBooks();
    }

    // Optional: Get all currently borrowed books by a specific student
    @GetMapping("/currently-borrowed/by-student")
    public List<BorrowedBook> getCurrentlyBorrowedBooksByStudent(@RequestParam Long studentId) {
        return orderService.getCurrentlyBorrowedBooksByStudent(studentId);
    }

    // Optional: Get all currently borrowed books of a specific book
    @GetMapping("/currently-borrowed/by-book")
    public List<BorrowedBook> getCurrentlyBorrowedBooksByBook(@RequestParam Long bookId) {
        return orderService.getCurrentlyBorrowedBooksByBook(bookId);
    }


}
