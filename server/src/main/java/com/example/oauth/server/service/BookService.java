package com.example.oauth.server.service;

import com.example.oauth.server.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BookService {

    private List<Book> books;

    public BookService() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(UUID.randomUUID().toString(), "Jane Eyre", "Charlotte Brontë"));
        books.add(new Book(UUID.randomUUID().toString(), "1984", "George Orwell"));
        books.add(new Book(UUID.randomUUID().toString(), "A Brief History Of Time", "Stephen Hawking"));
        this.books = books;
    }

    public Book getBookById(String id) {
        for (Book book: books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }
}
