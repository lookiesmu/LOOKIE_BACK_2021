package controller;

import JWT.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import vo.UserVO;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class LoginController {
    private final UserService userService;
    private final JWTTokenProvider tokenProvider;

    @GetMapping
    public String getLogin(){
        return "login";
    }

    @PostMapping
    public @ResponseBody ResponseEntity<?> postLogin(@RequestBody UserVO userVO){
        Map<String, String> body = new HashMap<>();

        if(userService.checkPassword(userVO.getEmail(),userVO.getPassword())) {
            UserVO user = userService.findByEmail(userVO.getEmail());
            body.put("token", tokenProvider.createToken(user.getId(), ((List<GrantedAuthority>) user.getAuthorities()).get(0).getAuthority()));
        }
        else
            body.put("token",null);

        return ResponseEntity.ok(body);

    }
/*
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
 */
}
