package sumdu.edu.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sumdu.edu.ua.model.Book;
import sumdu.edu.ua.port.CatalogRepositoryPort;
import java.util.List;

@RestController // Поєднує @Controller та @ResponseBody
@RequestMapping("/books") // Базовий шлях для всіх методів
public class BookController {

    private final CatalogRepositoryPort bookRepository;

    public BookController(CatalogRepositoryPort bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Value("${library.welcome-message:Вітаємо у бібліотеці!}")
    private String message;

    @Autowired
    private String appVersion;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/welcome")
    public String getWelcome() {
        return message;
    }

    @GetMapping("/version")
    public String getVersion() {
        return appVersion;
    }
}
