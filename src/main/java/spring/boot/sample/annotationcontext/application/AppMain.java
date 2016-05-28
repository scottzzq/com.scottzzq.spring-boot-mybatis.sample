package spring.boot.sample.annotationcontext.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;

public class AppMain {
	public static void main(String[] args) {
		for (String arg: args){
			System.out.println(arg);
		}
		SimpleCommandLinePropertySource ps = new SimpleCommandLinePropertySource(args);
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().getPropertySources().addFirst(ps);
		ctx.register(ApplicationConfig.class);
		ctx.refresh();
		System.out.println(ctx.getBean(MyComponent.class).getMyPropertyData());
	}
}
