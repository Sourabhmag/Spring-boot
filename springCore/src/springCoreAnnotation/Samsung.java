package springCoreAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Samsung {
	@Autowired
	@Qualifier("mediaTek")
	private MobileProcessor processor;

	public MobileProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(MobileProcessor processor) {
		this.processor = processor;
	}
	
	public void mobile()
	{
		System.out.println("We are one of best mobile brand in the world");
		processor.processor();
	}
}
