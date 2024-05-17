package avito.repository;

import avito.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gturumtaev
 */
@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {

}
