package com.example.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends ResourceConfig {
	
	public Application() {
		register(new StringCollection());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
