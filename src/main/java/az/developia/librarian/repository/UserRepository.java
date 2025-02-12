package az.developia.librarian.repository;

import az.developia.librarian.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

//    // Search by name and role (case-insensitive)
//    List<User> findByNameContainingIgnoreCaseAndRole(String name, String role);
//
//    // Search by email and role (case-insensitive)
//    List<User> findByEmailContainingIgnoreCaseAndRole(String email, String role);
//
//    // Find all users by role
//    List<User> findByRole(String role);
}
