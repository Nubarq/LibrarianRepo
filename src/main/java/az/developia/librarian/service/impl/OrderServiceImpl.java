package az.developia.librarian.service.impl;

import az.developia.librarian.entity.BorrowedBook;
import az.developia.librarian.repository.BorrowedBookRepository;
import az.developia.librarian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

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
