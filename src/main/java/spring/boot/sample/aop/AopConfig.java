package spring.boot.sample.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("sample.mybatis.aop")
@EnableAspectJAutoProxy //1
public class AopConfig {

}
