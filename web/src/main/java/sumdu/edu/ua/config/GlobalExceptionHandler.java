package sumdu.edu.ua.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // цей клас ловить помилки по всьому проєкту
public class GlobalExceptionHandler {

    // Цей метод спрацює, коли десь у коді виникне IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        // Повертаємо статус 400 (Bad Request) і текст помилки, який ми писали в сервісі
        return ResponseEntity.badRequest().body("Помилка валідації: " + ex.getMessage());
    }

    // Можна також додати загальний обробник для всіх інших помилок
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception ex) {
        return ResponseEntity.internalServerError().body("Сталася непередбачена помилка: " + ex.getMessage());
    }
}