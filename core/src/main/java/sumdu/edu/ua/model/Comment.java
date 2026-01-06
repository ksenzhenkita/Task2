package sumdu.edu.ua.model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Long bookId;
    private String text;
    private LocalDateTime createdAt;

    public Comment(Long id, Long bookId, String text, LocalDateTime createdAt) {
        this.id = id;
        this.bookId = bookId;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getText() { return text; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}