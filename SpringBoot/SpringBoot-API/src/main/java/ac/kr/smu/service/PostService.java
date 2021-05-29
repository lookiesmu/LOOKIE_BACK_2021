package ac.kr.smu.service;

import ac.kr.smu.domain.entity.Post;
import ac.kr.smu.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Page<Post> getPostList(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <=0 ? 0: pageable.getPageNumber()-1,
                pageable.getPageSize());
        Page<Post> postList = postRepository.findAll(pageable);

        return postList;
    }
}
