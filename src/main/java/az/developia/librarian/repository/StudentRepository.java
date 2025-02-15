package az.developia.librarian.repository;

import az.developia.librarian.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByEmail(String email);

    // Search by name and role (case-insensitive)
    List<Student> findByNameContainingIgnoreCase(String name);

    // Search by email and role (case-insensitive)
    List<Student> findByEmailContainingIgnoreCase(String email);

}
