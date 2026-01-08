package sumdu.edu.ua.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sumdu.edu.ua.model.Comment;
import sumdu.edu.ua.model.*;
import sumdu.edu.ua.port.CommentRepositoryPort;

import java.sql.*;
import java.time.LocalDateTime;

@Repository
public class JdbcCommentRepository implements CommentRepositoryPort {

    @Autowired // Field Injection (тільки для демонстрації в лабі)
    private DbConfig dbConfig;

    @Override
    public void save(Comment comment) {
        String sql = "INSERT INTO comments (book_id, text, created_at) VALUES (?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, comment.getBookId()); // Додай це поле в модель Comment, якщо забув раніше
            pstmt.setString(2, comment.getText());
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Comment findById(Long id) {
        String sql = "SELECT * FROM comments WHERE id = ?";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Comment(
                        rs.getLong("id"),
                        rs.getLong("book_id"),
                        rs.getString("text"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public void delete(Long id) {
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM comments WHERE id = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}