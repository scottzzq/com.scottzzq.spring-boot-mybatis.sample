package spring.boot.sample.aop;

import org.springframework.stereotype.Service;

@Service
public class DemoAnnotationService {
	@Action(name="aopAction")
    public void add(){} 
}
