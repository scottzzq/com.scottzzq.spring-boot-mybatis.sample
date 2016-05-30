package demo.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("demo.sso.server")
@SpringBootApplication
@EnableScheduling
public class AppMain {
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}