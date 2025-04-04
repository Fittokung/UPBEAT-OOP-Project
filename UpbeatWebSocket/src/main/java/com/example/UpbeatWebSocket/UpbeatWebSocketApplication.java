package com.example.UpbeatWebSocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class UpbeatWebSocketApplication {

	public static void main(String[] args) {

//		SpringApplication.run(UpbeatWebSocketApplication.class, args);
		SpringApplication app = new SpringApplication(UpbeatWebSocketApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
		app.run(args);
	}
}

