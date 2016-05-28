package spring.boot.sample.annotationcontext.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("sample.mybatis.test")
@PropertySource(value = {"classpath:/application.properties", "file:${config.location}"}, ignoreResourceNotFound = true)
public class ApplicationConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}