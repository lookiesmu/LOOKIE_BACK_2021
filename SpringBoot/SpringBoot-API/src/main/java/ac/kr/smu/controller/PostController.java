package ac.kr.smu.controller;

import ac.kr.smu.domain.entity.Post;
import ac.kr.smu.domain.repository.PostRepository;
import ac.kr.smu.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> getPost(@PageableDefault Pageable pageable){
        Page<Post> postList = postService.getPostList(pageable);
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(pageable.getPageSize(),
                postList.getNumber(),postList.getTotalElements());
        PagedModel<Post> body = PagedModel.of(postList.getContent(),pageMetadata);

        body.add(linkTo(methodOn(PostController.class).getPost(pageable)).withSelfRel());
        return ResponseEntity.ok(body);
    }
}