package sumdu.edu.ua.model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Long bookId;
    private String text;
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(Long id, Long bookId, String text, LocalDateTime createdAt) {
        this.id = id;
        this.bookId = bookId;
        this.text = text;
        this.createdAt = createdAt;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Long getBookId() { return bookId; }
    public String getText() {
        return text; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}