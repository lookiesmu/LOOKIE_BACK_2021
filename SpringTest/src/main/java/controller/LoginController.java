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
    //private final UserService userService;
    //private final JWTTokenProvider tokenProvider;

    @GetMapping
    public String getLogin(){
        return "login";
    }

    /*
    //JWT
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
     */

    /*
    @PostMapping
    public ResponseEntity<?> postLogin(@RequestBody UserVO userVO, HttpSession session){
        Map<String,Boolean> body = new HashMap<>();
        boolean result = userService.checkPassword(userVO);

        body.put("success",result);
        if(result) {

            session.setMaxInactiveInterval(60*30);

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