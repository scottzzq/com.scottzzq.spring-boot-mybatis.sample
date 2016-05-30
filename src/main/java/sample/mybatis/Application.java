package sample.mybatis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序的入口
 *
 * @author 小翼
 * @version 1.0.0
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("这是一个MyBatis和Spring-Boot整合的Demo,通过JPA连接默认数据库，通过Mybatis连接自定义增加的数据库");
	}
}