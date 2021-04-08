package ac.kr.smu.controller;

import ac.kr.smu.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private PostService postService;
    
    @GetMapping // board로 들어오는 get 메소드를 처리
    public void getBoard(Model model){
        model.addAttribute("postList", postService.findAll());
    }
}
