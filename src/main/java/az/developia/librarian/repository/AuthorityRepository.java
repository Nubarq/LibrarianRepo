package az.developia.librarian.repository;

import az.developia.librarian.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

@Transactional
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query(value = "INSERT INTO authorities (user_id, authority) " +
            "SELECT ?1, authority FROM authority_list WHERE librarian = 1",
            nativeQuery = true)
    @Modifying
    void addLibrarianAuthorities(Integer userId);

    @Query(value = "INSERT INTO authorities (user_id, authority) " +
            "SELECT ?1, authority FROM authority_list WHERE student = 1",
            nativeQuery = true)
    @Modifying
    void addStudentAuthorities(Integer userId);
}
