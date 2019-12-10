package com.bridgelabz.fundoouser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import com.bridgelabz.fundoouser.dto.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
@EnableCaching(proxyTargetClass = true)
public class FundooUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundooUserApplication.class, args);
	}

}
