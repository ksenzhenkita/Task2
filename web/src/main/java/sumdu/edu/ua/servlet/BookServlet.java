package sumdu.edu.ua.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sumdu.edu.ua.dto.ErrorResponse;
import sumdu.edu.ua.repository.JdbcBookRepository;
import sumdu.edu.ua.model.Book;

import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(BookServlet.class);
    private final JdbcBookRepository repository = new JdbcBookRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");

        try {
            // Отримуємо параметри q, page, size (як вимагає завдання)
            String query = req.getParameter("q");
            String page = req.getParameter("page");

            // Для простоти зараз повернемо всі книги
            List<Book> books = repository.findAll();

            log.info("Successfully retrieved {} books", books.size());
            resp.setStatus(HttpServletResponse.SC_OK);
            objectMapper.writeValue(resp.getWriter(), books);

        } catch (Exception e) {
            log.error("Internal server error", e);
            resp.setStatus(500);
            objectMapper.writeValue(resp.getWriter(), new ErrorResponse(500, "Серверна помилка"));
        }
    }
}