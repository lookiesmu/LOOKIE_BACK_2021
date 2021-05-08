package controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import vo.UserVO;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController {
    private final UserService userService;

    @GetMapping
    public ModelAndView getLogin(){
        return new ModelAndView("login");
    }

    @PostMapping
    public ResponseEntity<?> postLogin(@RequestBody UserVO userVO, HttpSession session){
        Map<String,Boolean> body = new HashMap<>();
        boolean result = userService.checkPassword(userVO);

        body.put("success",result);
        if(result) {
            //세션 만료 시간
            session.setMaxInactiveInterval(60*30);
            //세션 값 설정
            session.setAttribute("userSession",userVO.getEmail());
        }

        return ResponseEntity.ok(body);
    }

    @PostMapping("/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }
}
