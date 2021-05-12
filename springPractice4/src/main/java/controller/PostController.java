package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.PostService;
import vo.PostVO;
import vo.UserVO;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping({"/{postId}", ""})
    public String getPost(@PathVariable(value = "postId", required = false) Integer postId,
                          @RequestParam(value = "isModify", required = false) boolean isModify, Model model, HttpSession httpSession) {
        if (postId != null) {
            model.addAttribute("post", postService.findById(postId));
            model.addAttribute("userSession", httpSession.getAttribute("userSession"));
        }
        if (isModify)
            model.addAttribute("isModify", true);
        else
            model.addAttribute("isModify", false);
        return "post";
    }

    @PostMapping
    public @ResponseBody int postPost(@RequestBody PostVO postVO, UserVO user) {
        postVO.setUser(user);
        postService.save(postVO);
        return postVO.getId();
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
