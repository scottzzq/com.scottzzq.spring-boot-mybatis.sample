package spring.boot.sample.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by patterncat on 2016-01-24.
 */
@RestController
@RequestMapping("/session")
public class HelloController {
    @RequestMapping("/uid")
    String uid(HttpSession session) throws InterruptedException {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}