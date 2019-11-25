package springCoreAnnotation;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "springCoreAnnotation")
public class AppConfig {
	
	// We used ComponentScan annotation so we commented all beans 
	// This ComponentScan will scan all components and returns them as an bean object
	
	@Bean
	public Samsung getSamsung() {
		return new Samsung();
	}
	
//	@Bean
//	public Snapdragon getProcessor()
//	{
//		return new Snapdragon();
//	}
//	
//	@Bean
//	public Samsung getMobile()
//	{
//		return new Samsung();
//	}
}
