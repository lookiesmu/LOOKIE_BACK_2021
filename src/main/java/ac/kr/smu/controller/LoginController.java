package ac.kr.smu.controller;

import ac.kr.smu.jwt.JWTTokenProvider;
import ac.kr.smu.service.UserService;
import ac.kr.smu.vo.UserVO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
//
//    @PostMapping("/logout")
//    public void logout(HttpSession session){
//        session.invalidate();
//    }
}
