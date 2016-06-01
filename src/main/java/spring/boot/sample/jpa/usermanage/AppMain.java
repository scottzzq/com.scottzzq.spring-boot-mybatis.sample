package spring.boot.sample.jpa.usermanage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AppMain implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(AppMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("jpa sample");
	}
}