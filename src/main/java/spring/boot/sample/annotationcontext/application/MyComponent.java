package spring.boot.sample.annotationcontext.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
	@Value("${my.property.data}")
	String myPropertyData;

	public String getMyPropertyData() {
		return myPropertyData;
	}
}
