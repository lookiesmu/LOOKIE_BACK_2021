import ac.kr.smu.BoardApplication;
import ac.kr.smu.domain.File;
import ac.kr.smu.domain.Post;
import ac.kr.smu.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ac.kr.smu.repository.FileRepository;
import ac.kr.smu.repository.PostRepository;
import ac.kr.smu.repository.UserRepository;

import java.util.UUID;

@SpringBootTest(classes = BoardApplication.class)
public class BoardTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;

    @Test
    public void test(){
        System.out.println("test");
        User user = User.builder().name("test").password("test").email("test").build();
        Post post = Post.builder().title("test").content("test").user(user).build();
        File file = File.builder().name("test").uuid(UUID.randomUUID().toString()).upload_path("/").post(post).build();

        userRepository.save(user);
        postRepository.save(post);
        fileRepository.save(file);
    }

}