package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Service.BookService;
import pl.coderslab.model.Book;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    public BookService bookService;


    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @RequestMapping("/hello")
    public String hello() {
        return "{hello: World}";
    }

    @RequestMapping("")
    public List<Book> listBooks() {
        return bookService.getList();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
        return book;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return "Deleted book " + id;
    }
}
