package ac.kr.smu.controller;

import ac.kr.smu.service.UserService;
import ac.kr.smu.vo.UserVO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {
//    private final UserService userService;

    @GetMapping
    public String getLogin(){
        return "login";
    }
//    @PostMapping
//    public ResponseEntity<?> postLogin(@RequestBody UserVO userVO, HttpSession session){
//        Map<String,Boolean> body = new HashMap<>();
//        boolean result = userService.checkPassword(userVO.getEmail(), userVO.getPassword());
//
//        body.put("success",result);
//        if(result) {
//            session.setMaxInactiveInterval(60*30);//초 단위로 세션의 만료시간 설정
//            session.setAttribute("userSession",userVO.getEmail());
//        }
//
//        return ResponseEntity.ok(body);
//
//    }
//    @PostMapping("/logout")
//    public void logout(HttpSession session){
//        session.invalidate();
//    }
}
