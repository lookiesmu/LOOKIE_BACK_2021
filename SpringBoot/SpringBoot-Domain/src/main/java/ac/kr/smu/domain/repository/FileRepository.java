package ac.kr.smu.domain.repository;

import ac.kr.smu.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
