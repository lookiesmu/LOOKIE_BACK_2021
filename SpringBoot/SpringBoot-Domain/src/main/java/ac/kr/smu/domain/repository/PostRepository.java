package ac.kr.smu.domain.repository;

import ac.kr.smu.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
