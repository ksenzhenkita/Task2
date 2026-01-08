package sumdu.edu.ua.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DbConfig {
    // Шлях до бази в пам'яті (вона видаляється після зупинки програми)
    private final String URL = "jdbc:h2:mem:library;DB_CLOSE_DELAY=-1";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "sa", "");
    }

    @PostConstruct
    // Метод для створення таблиць при запуску
    public void initDatabase() {
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS books (id BIGINT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), author VARCHAR(255))");
            st.execute("CREATE TABLE IF NOT EXISTS comments (id BIGINT AUTO_INCREMENT PRIMARY KEY, book_id BIGINT, text VARCHAR(1000), created_at TIMESTAMP)");

            // Додамо одну книгу для тесту
            st.execute("INSERT INTO books (title, author) VALUES ('Java Beginner Guide', 'Herbert Schildt')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}