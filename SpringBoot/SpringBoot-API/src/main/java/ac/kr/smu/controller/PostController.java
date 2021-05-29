package ac.kr.smu.controller;

import ac.kr.smu.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostRepository postRepository;

    @GetMapping
    public ResponseEntity<?> getPost(){
        return ResponseEntity.ok(postRepository.findAll());
    }
}