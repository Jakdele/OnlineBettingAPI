package pl.coderslab.Service;

import org.springframework.stereotype.Service;
import pl.coderslab.model.Book;

import java.util.List;

@Service
public interface BookService {
    public List<Book> getList();
    public void addBook(Book book);
    public Book getBookById(long id);
    public void updateBook(long id, Book book);
    public void deleteBook(long id);
}
