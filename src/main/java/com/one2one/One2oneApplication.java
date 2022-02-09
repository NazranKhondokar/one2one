package com.one2one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class One2oneApplication {

	@GetMapping("/")
	public String home() {
		return "Welcome to One2One";
	}

	public static void main(String[] args) {
		SpringApplication.run(One2oneApplication.class, args);
	}

}
