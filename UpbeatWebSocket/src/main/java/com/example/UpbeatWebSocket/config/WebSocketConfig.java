package com.example.UpbeatWebSocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // กำหนด Path ให้ Client สามารถเชื่อมต่อ Server
    // localhost:8080/ws ถ้าจะ connect
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //ws Can be anything
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    // กำหนด Sever จะรับผ่านทางไหน แล้วถ้าส่งจะส่งผ่านทางไหน
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // Path ที่จะรับข้อความจาก Client และแนบ body

        registry.enableSimpleBroker("/topic"); // Path จะส่งข้อมูลผ่านทางไหน
    }
}
