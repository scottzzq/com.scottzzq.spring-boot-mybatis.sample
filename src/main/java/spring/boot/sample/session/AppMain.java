package spring.boot.sample.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@ComponentScan("spring.boot.sample.session")
@SpringBootApplication
@EnableRedisHttpSession
public class AppMain {
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}