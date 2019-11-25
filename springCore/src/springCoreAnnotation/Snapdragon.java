package springCoreAnnotation;

import org.springframework.stereotype.Component;

@Component
public class Snapdragon implements MobileProcessor {

	@Override
	public void processor() {
		System.out.println("We just launched Snapdragon 855 processor");
		
	}

}
