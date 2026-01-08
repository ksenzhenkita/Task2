package sumdu.edu.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sumdu.edu.ua.model.Book;
import sumdu.edu.ua.model.Comment;
import sumdu.edu.ua.port.CatalogRepositoryPort;
import sumdu.edu.ua.service.BookService;

import java.util.List;

@RestController // Поєднує @Controller та @ResponseBody
@RequestMapping("/books") // Базовий шлях для всіх методів
public class BookController {

    private final CatalogRepositoryPort bookRepository;

    @Autowired
    private BookService bookService;

    public BookController(CatalogRepositoryPort bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @Value("${library.welcome-message:Вітаємо у бібліотеці!}")
    private String message;

    @Autowired
    private String appVersion;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}") // Маршрутизація для /books/1
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build(); // Повернення 404, якщо книги немає
        }
        return ResponseEntity.ok(book); // Повернення даних у JSON форматі
    }

    @GetMapping("/welcome")
    public String getWelcome() {
        return message;
    }

    @GetMapping("/version")
    public String getVersion() {
        return appVersion;
    }

    @PostMapping("/comments")
    public ResponseEntity<String> addComment(@RequestBody Comment comment) {
        System.out.println("DEBUG: Отримано коментар: bookId=" + comment.getBookId() + ", text=" + comment.getText());
        bookService.addComment(comment.getBookId(), comment.getText());
        return ResponseEntity.ok("Comment added successfully");
    }
}
