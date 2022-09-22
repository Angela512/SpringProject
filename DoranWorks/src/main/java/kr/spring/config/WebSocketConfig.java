package kr.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import kr.spring.websocket.ws.AlarmHandler;
import kr.spring.websocket.ws.ChatHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler = new ChatHandler();
    private final AlarmHandler alarmHandler = new AlarmHandler();

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    
        registry.addHandler(chatHandler, "chat-ws.do").setAllowedOrigins("*");
        registry.addHandler(alarmHandler, "alarm-ws.do").setAllowedOrigins("*");
    }
}