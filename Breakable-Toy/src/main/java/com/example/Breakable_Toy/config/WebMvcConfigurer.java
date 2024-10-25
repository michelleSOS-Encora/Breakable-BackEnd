package com.example.Breakable_Toy.config;
import com.example.Breakable_Toy.websocket.ToDoWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
@Configuration
@EnableWebSocket
public interface WebMvcConfigurer extends WebSocketConfigurer {
    @Override
    default void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ToDoWebSocketHandler(), "/api/todos")
                .setAllowedOrigins("http://localhost:8083");
    }
}
