package spring.boot.sample.exception.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.boot.sample.exception.exception.MyException;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }
}