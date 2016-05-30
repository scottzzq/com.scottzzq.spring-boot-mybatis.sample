package spring.boot.sample.session;

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
    String uid(HttpSession session) {
    	SessionObject obj = new SessionObject();
    	obj.setName("zhaizhiqiang");
    	obj.setPassword("zhaizhiqiangnwpu");
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        session.setAttribute("obj", obj);
        return session.getId();
    }
}