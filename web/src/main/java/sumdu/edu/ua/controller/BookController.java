package sumdu.edu.ua.controller;

import org.springframework.web.bind.annotation.*;
import sumdu.edu.ua.model.Book;
import sumdu.edu.ua.port.CatalogRepositoryPort;
import java.util.List;

@RestController // Поєднує @Controller та @ResponseBody
@RequestMapping("/api/books") // Базовий шлях для всіх методів
public class BookController {

    private final CatalogRepositoryPort bookRepository;

    // Ін'єкція залежності через конструктор
    public BookController(CatalogRepositoryPort bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
