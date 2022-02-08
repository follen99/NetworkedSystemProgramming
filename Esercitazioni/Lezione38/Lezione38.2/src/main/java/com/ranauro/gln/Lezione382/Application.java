package com.ranauro.gln.Lezione382;

import com.ranauro.gln.mqtt.MqttSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		MqttSubscriber sub = new MqttSubscriber("tcp://127.0.0.1:1883");
		sub.subscribe();
	}

}
