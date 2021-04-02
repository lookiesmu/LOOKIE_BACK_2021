package ac.kr.smu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    
    @GetMapping // board로 들어오는 get 메소드를 처리
    public void getBoard(){}
}
