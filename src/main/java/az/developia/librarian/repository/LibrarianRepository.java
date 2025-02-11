package az.developia.librarian.repository;

import az.developia.librarian.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian,Integer> {
}
