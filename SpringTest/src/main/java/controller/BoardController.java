package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import service.PostService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    private PostService postService;

    @GetMapping
    public void getBoard(Model model){
        model.addAttribute("postList",postService.findAll());
    }
}