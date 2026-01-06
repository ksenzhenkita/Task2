package sumdu.edu.ua.port;

import sumdu.edu.ua.model.Book;
import java.util.List;

public interface CatalogRepositoryPort {
    List<Book> findAll();
    Book findById(Long id);
    void delete(Long id);
}