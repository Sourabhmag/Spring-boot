package springCoreAnnotation;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Component
@Aspect
@EnableAspectJAutoProxy
public class MediaTek implements MobileProcessor {

	@Override
	public void processor() {
		System.out.println("We are with MI phones");
		
	}

}
