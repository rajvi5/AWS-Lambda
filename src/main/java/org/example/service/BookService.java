package org.example.service;

import org.example.dto.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    //Create new book
    public void addBook(Book book)
    {
        books.add(book);
    }

    //Retrieve all books
    public List<Book> getBooks()
    {
        return books;
    }

    //Retrieve book by id
    public Optional<Book> getBookById(int id)
    {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    //update book
    public boolean updateBook(int id, Book newBook)
    {
        return getBookById(id).map(existingBook -> {
            books.remove(existingBook);
            books.add(newBook);
            return true;
        }).orElse(false);
    }

    //delete book
    public boolean deleteBook(int id)
    {
        return books.removeIf(book -> book.getId() == id);
    }

}
