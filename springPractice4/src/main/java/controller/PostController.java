package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.PostService;
import vo.PostVO;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping({"/{postId}", ""})
    public String getPost(@PathVariable(value = "postId", required = false) Integer postId,
                          @RequestParam(value = "isModify", required = false) boolean isModify, Model model) {
        if (postId != null)
            model.addAttribute("post", postService.findById(postId));
        if (isModify)
            model.addAttribute("isModify", true);
        else
            model.addAttribute("isModify", false);

        return "post";
    }

    //작성 후 redirect
    @PostMapping
    public String postPost(PostVO postVO){
        postService.save(postVO);
        return "redirect:/board";
    }

    @PutMapping
    public @ResponseBody boolean putPost(@RequestBody PostVO postVO, Model model) {
        model.addAttribute("post", postService.update(postVO));
        model.addAttribute("isModify", false);
        return true;
    }

    @DeleteMapping("/{postId}")
    public @ResponseBody boolean deletePost(@PathVariable int postId){
        postService.delete(postId);
        return true;
    }
}
