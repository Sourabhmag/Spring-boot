package springCoreAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Samsung phone = (Samsung)context.getBean("getSamsung",Samsung.class);
		phone.getProcessor();
		phone.mobile();
		((ConfigurableApplicationContext)context).close();
	} 

}
