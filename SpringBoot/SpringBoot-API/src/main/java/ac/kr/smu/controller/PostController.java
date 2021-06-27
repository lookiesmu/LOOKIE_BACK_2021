package ac.kr.smu.controller;

import ac.kr.smu.assembler.PostAssembler;
import ac.kr.smu.resource.PostResource;
import ac.kr.smu.service.PostService;
import ac.kr.smu.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
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
    private final PostAssembler postAssembler;

    @GetMapping
    public ResponseEntity<?> getPost(@PageableDefault Pageable pageable){
        Page<Post> postList = postService.getPostList(pageable);
        CollectionModel<PostResource> collectionModel = postAssembler.toCollectionModel(postList);
        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(pageable.getPageSize(),postList.getNumber(),postList.getTotalElements());
        PagedModel<PostResource> body = PagedModel.of(collectionModel.getContent(),pageMetadata);

        body.add(linkTo(methodOn(PostController.class).getPost(pageable)).withSelfRel());
        return ResponseEntity.ok(body);
    }
}