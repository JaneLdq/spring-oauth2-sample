package com.example.oauth.server.controller;

import com.example.oauth.server.entity.Book;
import com.example.oauth.server.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(value = "/api/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }
}
