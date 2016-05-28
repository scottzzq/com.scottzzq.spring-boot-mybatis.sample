package spring.boot.sample.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		 AnnotationConfigApplicationContext context =
	                new AnnotationConfigApplicationContext(AopConfig.class); //1
		 
		 DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
		 
		 DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
		 
		 demoAnnotationService.add();
		 
		 demoMethodService.add();

		System.out.println("test");
		 context.close();
	}

}
