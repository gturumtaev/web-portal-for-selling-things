package avito.repository;

import avito.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gturumtaev
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String name);
}
