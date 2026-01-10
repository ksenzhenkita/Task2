package sumdu.edu.ua.repository;

import org.springframework.stereotype.Repository;
import sumdu.edu.ua.model.Book;
import sumdu.edu.ua.port.CatalogRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcBookRepository implements CatalogRepositoryPort {

    private final DbConfig dbConfig;

    public JdbcBookRepository(DbConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Override
    public Book findById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = dbConfig.getConnection(); // Використовуємо поле dbConfig (не статично)
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Book(rs.getLong("id"), rs.getString("title"), rs.getString("author"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = dbConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}