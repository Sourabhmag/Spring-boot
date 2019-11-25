package com.bridgelabz.fundoouser.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/******************************************************************************
 *  Compilation:  javac -d bin Config.java
 *  Execution:    
 *              
 *  
 *  Purpose:       main purpose this class create for 
 *  				configuration modelmapper and token
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   19-11-2019
 *
 ******************************************************************************/

@Configuration
public class Config {

	// Create and returns object of Model Mapper
	@Bean
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}
}
