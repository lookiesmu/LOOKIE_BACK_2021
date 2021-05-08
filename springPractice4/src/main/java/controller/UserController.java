package controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import vo.UserVO;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ModelAndView getUser(){
        return new ModelAndView("register");
    }

    @PostMapping
    public Map<String,String> postUser(@RequestBody UserVO userVO){
        Map<String, String> result = new HashMap<>();

        //중복 확인
        if(!userService.checkEmailDuplication(userVO.getEmail())){
            result.put("error","duplication");
            return result;
        }

        //중복 없다면
        userService.save(userVO);
        result.put("error",null);
        return result;
    }
}