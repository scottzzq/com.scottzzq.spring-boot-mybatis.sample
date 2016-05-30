package spring.boot.sample.interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@ComponentScan("spring.boot.sample.interceptor")
@SpringBootApplication
@EnableRedisHttpSession
public class AppMain {
	//--logging.config=
	//--spring.config.location=
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}