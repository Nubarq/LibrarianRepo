package az.developia.librarian.repository;

import az.developia.librarian.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Integer> {
}
