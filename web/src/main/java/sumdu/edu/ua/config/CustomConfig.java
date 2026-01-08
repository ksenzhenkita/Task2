package sumdu.edu.ua.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {
    @Bean
    public String welcomeMessage() {
        return "Welcome to Spring Boot Library!";
    }
}
