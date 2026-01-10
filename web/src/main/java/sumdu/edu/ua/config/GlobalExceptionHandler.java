//package sumdu.edu.ua.config;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice // цей клас ловить помилки по всьому проєкту
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    // Додаємо тип контенту з кодуванням UTF-8
//    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
//        return ResponseEntity
//                .badRequest()
//                .header("Content-Type", "text/plain; charset=UTF-8") // Явно вказуємо кодування
//                .body("Помилка валідації: " + ex.getMessage());
//    }
//
//    // Можна також додати загальний обробник для всіх інших помилок
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralError(Exception ex) {
//        return ResponseEntity.internalServerError().body("Сталася непередбачена помилка: " + ex.getMessage());
//    }
//}