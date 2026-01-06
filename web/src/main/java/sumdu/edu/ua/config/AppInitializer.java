package sumdu.edu.ua.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import sumdu.edu.ua.repository.DbConfig;

@WebListener
public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Викликаємо метод створення таблиць, який ми написали раніше
        DbConfig.initDatabase();
        System.out.println("База даних ініціалізована!");
    }
}