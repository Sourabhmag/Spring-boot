package com.bridgelabz.fundoouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.bridgelabz.fundoouser.dto.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class FundooUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundooUserApplication.class, args);
	}

}
