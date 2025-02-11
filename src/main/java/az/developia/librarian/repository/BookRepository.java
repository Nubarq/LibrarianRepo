package az.developia.librarian.repository;

import az.developia.librarian.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    // Search by title (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Search by author (case-insensitive)
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Search by ISBN (case-insensitive)
    List<Book> findByIsbnContainingIgnoreCase(String isbn);
}
