package org.example.controller;

import org.example.dto.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        bookService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Book> getBookById(@PathVariable int id)
    {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Book> getBookById(@PathVariable int id, @RequestBody Book newBook)
    {
        boolean updated = bookService.updateBook(id, newBook);
        if(updated)
        {
            return new ResponseEntity<>(newBook, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(newBook, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteBook(@PathVariable int id)
    {
        boolean deleted = bookService.deleteBook(id);
        if(deleted)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
