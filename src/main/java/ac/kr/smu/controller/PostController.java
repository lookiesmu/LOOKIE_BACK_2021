package ac.kr.smu.controller;

import ac.kr.smu.service.PostService;
import ac.kr.smu.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public void getPost(){}

    @PostMapping
    public String postPost(PostVO postVO){
        postService.save(postVO);
        return "redirect:/board";
    }
}