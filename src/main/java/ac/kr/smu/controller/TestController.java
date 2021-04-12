package ac.kr.smu.controller;

import ac.kr.smu.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class TestController {
    @Autowired
    private PostMapper testMapper;
    @GetMapping
    public String getTest(){
        return "test";
    }
}
