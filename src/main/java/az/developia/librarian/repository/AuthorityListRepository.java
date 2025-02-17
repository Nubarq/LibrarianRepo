package az.developia.librarian.repository;

import az.developia.librarian.entity.AuthorityList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityListRepository extends JpaRepository<AuthorityList, Long> {
}
