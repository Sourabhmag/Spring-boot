package com.bridgelabz.NoteService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/******************************************************************************
 *  Compilation:  javac -d bin RedisConfig.java
 *  Execution:    
 *              
 *  
 *  Purpose:       main purpose this class is to configure Swagger
 *
 *  @author  Sourabh Magdum
 *  @version 1.0
 *  @since   09-12-2019
 *
 ******************************************************************************/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{
   @Bean
   public Docket productApi()
   {
       return new Docket(DocumentationType.SWAGGER_2)
        .select()
               .apis(RequestHandlerSelectors.basePackage("com.bridgelabz.NoteService"))
               .build();

   }

}
