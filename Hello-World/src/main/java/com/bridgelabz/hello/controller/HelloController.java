package com.bridgelabz.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/app")
	public String hello()
	{
		return "Hello Sourabh.How are you?And i am fine";
	}
}
