package az.developia.librarian.repository;

import az.developia.librarian.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Integer> {

    // Find all borrowed books that are not yet returned
    List<BorrowedBook> findByReturnedDateIsNull();

    // Find all borrowed books that have been returned
    List<BorrowedBook> findByReturnedDateIsNotNull();
}
