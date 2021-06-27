package ac.kr.smu.resource;

import ac.kr.smu.domain.File;
import ac.kr.smu.domain.Post;
import ac.kr.smu.domain.User;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
public class PostResource extends RepresentationModel<PostResource> {
    private Long id;
    private String title;
    private String content;
    private User user;
    private List<File> fileList;

    public PostResource(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = post.getUser();
        this.fileList = post.getFileList();
    }
}
