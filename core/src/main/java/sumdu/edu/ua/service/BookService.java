package sumdu.edu.ua.service;

import org.springframework.stereotype.Service;
import sumdu.edu.ua.model.Book;
import sumdu.edu.ua.model.Comment;
import sumdu.edu.ua.port.CatalogRepositoryPort;
import sumdu.edu.ua.port.CommentRepositoryPort;
import java.time.LocalDateTime;

@Service
public class BookService {
    private final CommentRepositoryPort commentRepository;
    private final CatalogRepositoryPort catalogRepository;

    public BookService(CommentRepositoryPort commentRepository, CatalogRepositoryPort catalogRepository) {
        this.commentRepository = commentRepository;
        this.catalogRepository = catalogRepository;
    }

    // Валідація: назва книги не може бути порожньою
    public void validateBookTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва книги не може бути порожньою!");
        }
    }

    // Метод для отримання книги за ID (логіку пошуку реалізує persistence)
    public Book getBookById(Long id) {
        // Валідація ідентифікатора згідно з завданням
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Некоректний ID книги");
        }

        // Використовуємо поле КЛАСУ, а не створюємо нову змінну
        return catalogRepository.findById(id);
    }

    // Бізнес-правило: видалення лише протягом 24 годин
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId);
        if (comment == null) return;

        if (comment.getCreatedAt().isBefore(LocalDateTime.now().minusHours(24))) {
            throw new IllegalStateException("Коментар можна видалити лише протягом 24 годин!");
        }

        commentRepository.delete(commentId);
    }
}