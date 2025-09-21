package com.nitish.lb_app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LbApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(LbApp1Application.class, args);
	}

	@GetMapping("/")
	public String getResponse() {
		return "This is a response from App 1";
	}
}
