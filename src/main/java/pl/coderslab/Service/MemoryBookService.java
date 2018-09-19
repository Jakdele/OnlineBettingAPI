package pl.coderslab.Service;

import org.springframework.stereotype.Service;
import pl.coderslab.model.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryBookService implements BookService {
    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }
    public List<Book> getList() {return list;}
    public void setList(List<Book> list) {this.list = list;}

    public Book getBookById(long id){
     Book newBook = this.list.stream().filter(b-> b.getId()==id).findFirst().get();
     return newBook;
    }

    public void addBook(Book book){
        Book lastBook = this.list.get(list.size()-1);
        book.setId(lastBook.getId()+1);
        this.list.add(book);
    }
    public void updateBook(long id, Book book){
        Book newbook = this.list.stream().filter(item -> item.getId()==id).findFirst().get();
        newbook.setIsbn(book.getIsbn());
        newbook.setTitle(book.getTitle());
        newbook.setAuthor(book.getAuthor());
        newbook.setPublisher(book.getPublisher());
        newbook.setType(book.getType());
    }
    public void deleteBook(long id){
        this.list.remove(getBookById(id));
    }

}
