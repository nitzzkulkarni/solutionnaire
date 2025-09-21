package com.nitish.lb_app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LbApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(LbApp2Application.class, args);
	}

	@GetMapping("/")
	public String getResponse() {
		return "This is a response from App 2";
	}
}
