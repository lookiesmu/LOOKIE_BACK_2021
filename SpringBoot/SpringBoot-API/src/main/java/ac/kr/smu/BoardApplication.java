package ac.kr.smu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ac.kr.smu.domain.repository")
@EntityScan("ac.kr.smu.domain")
public class BoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class,args);
    }
}