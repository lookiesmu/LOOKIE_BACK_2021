package ac.kr.smu.domain.repository;

import ac.kr.smu.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}