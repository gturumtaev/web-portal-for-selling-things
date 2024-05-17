package avito.repository;

import avito.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gturumtaev
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByAdPk(Integer adPk);
}

