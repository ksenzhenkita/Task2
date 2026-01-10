package sumdu.edu.ua.web;

import sumdu.edu.ua.repository.DbConfig;
import sumdu.edu.ua.repository.JdbcBookRepository;
import sumdu.edu.ua.repository.JdbcCommentRepository;
import sumdu.edu.ua.service.BookService;

public class Infrastructure {
    private final BookService bookService;

    public Infrastructure() {
        // 1. Ручне налаштування бази (те, що робив @PostConstruct)
        DbConfig dbConfig = new DbConfig();
        dbConfig.initDatabase();

        // 2. Ручне створення репозиторіїв (те, що робив @Repository)
        JdbcBookRepository bookRepo = new JdbcBookRepository(dbConfig);
        JdbcCommentRepository commentRepo = new JdbcCommentRepository(dbConfig);

        // 3. Ручне створення сервісу (те, що робив @Service)
        this.bookService = new BookService(bookRepo, commentRepo);
    }

    public BookService getBookService() {
        return bookService;
    }
}
