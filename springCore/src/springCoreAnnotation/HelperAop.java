package springCoreAnnotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class HelperAop {
	
	@Before("execution(public void mobile())")
	@After("execution(public void mobile())")
	public void show()
	{
		System.out.println("I am helper class and running with the help of aop");
	}
}
