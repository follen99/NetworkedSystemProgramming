package com.ranauro.gln;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.glassfish.jersey.server.ResourceConfig;

@SpringBootApplication
public class Application extends ResourceConfig{
	public Application() {
		register(Products.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
