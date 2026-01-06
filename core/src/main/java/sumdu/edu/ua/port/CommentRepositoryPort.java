package sumdu.edu.ua.port;

import sumdu.edu.ua.model.Comment;

public interface CommentRepositoryPort {
    void save(Comment comment);
    void delete(Long id);
    Comment findById(Long id);
}