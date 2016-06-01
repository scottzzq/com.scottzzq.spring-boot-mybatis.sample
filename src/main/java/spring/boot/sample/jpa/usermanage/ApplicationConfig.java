package spring.boot.sample.jpa.usermanage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("spring.boot.sample.jpa.usermanage")
@PropertySource(value = {"classpath:/application.properties", "file:${config.location}"}, ignoreResourceNotFound = true)
public class ApplicationConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}