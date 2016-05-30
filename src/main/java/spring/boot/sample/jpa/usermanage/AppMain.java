package spring.boot.sample.jpa.usermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("spring.boot.sample.jpa")
public class AppMain {
	public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}