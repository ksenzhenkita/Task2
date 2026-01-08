package sumdu.edu.ua.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean // Створюємо кастомний бін, який можна буде заін'єктити будь-де
    public String appVersion() {
        return "Library App v3.0 (Spring Boot)";
    }
}
