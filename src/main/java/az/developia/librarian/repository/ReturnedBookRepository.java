package az.developia.librarian.repository;

import az.developia.librarian.entity.ReturnedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReturnedBookRepository extends JpaRepository<ReturnedBook,Integer> {
}
