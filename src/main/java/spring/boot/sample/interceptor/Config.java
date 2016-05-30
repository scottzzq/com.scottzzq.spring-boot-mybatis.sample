package spring.boot.sample.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 应用配置信息
 * 
 * @author Administrator
 *
 */
@Component
public class Config extends WebMvcConfigurerAdapter{
	 /** 
     * 配置拦截器 
     * @author lance 
     * @param registry 
     */  
	@Override
    public void addInterceptors(InterceptorRegistry registry) {  
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");  
    }  
}