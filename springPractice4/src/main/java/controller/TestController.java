package controller;

import lombok.extern.slf4j.Slf4j;
import mapper.PostMapper;
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
        //testMapper.test().stream().forEach(t -> log.info(t.toString()));
        return "test";
    }
}


