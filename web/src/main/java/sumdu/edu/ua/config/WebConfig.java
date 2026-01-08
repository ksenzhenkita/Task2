package sumdu.edu.ua.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc // Активуємо анотаційний режим MVC
public class WebConfig implements WebMvcConfigurer {
    // щоб налаштовувати конвертери, ресурси тощо
}