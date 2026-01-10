package sumdu.edu.ua.web;

import io.javalin.Javalin;
import sumdu.edu.ua.model.Comment;
import sumdu.edu.ua.service.BookService;

public class JavalinBookApp {
    public static void main(String[] args) {
        // Ініціалізуємо наші залежності вручну
        Infrastructure infra = new Infrastructure();
        BookService bookService = infra.getBookService();

        Javalin app = Javalin.create(config -> {
            config.requestLogger.http((ctx, ms) -> {
                System.out.println("Middleware Log: " + ctx.method() + " " + ctx.path());
            });
        }).start(8082);

        // --- Middleware (Виконується перед кожним запитом) ---
        app.before(ctx -> {
            ctx.header("Content-Type", "application/json; charset=UTF-8");
        });

        // --- Маршрути (Routes) ---

        // Список книг
        app.get("/books", ctx -> {
            ctx.json(bookService.getAllBooks());
        });

        // Книга за ID
        app.get("/books/{id}", ctx -> {
            long id = Long.parseLong(ctx.pathParam("id"));
            ctx.json(bookService.getBookById(id));
        });

        // Додавання коментаря
        app.post("/books/comments", ctx -> {
            Comment comment = ctx.bodyAsClass(Comment.class);
            bookService.addComment(comment.getBookId(), comment.getText());
            ctx.status(201).result("Comment added via Javalin!");
        });

        // --- Обробка помилок (Exception Handling) ---
        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            ctx.status(400).result("Javalin Error: " + e.getMessage());
        });
    }
}