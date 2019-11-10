package com.example.oauth.client.service;

import com.example.oauth.client.pojo.Book;
import com.example.oauth.client.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.util.List;

@Component
public class BookService {

    @Autowired
    private RestOperations bookServiceRestTemplate;

    @Value("${sample.resource.bookListURL")
    private String bookListURL;

    public List<Book> getBooks() {
        ResponseEntity<String> entity = bookServiceRestTemplate.getForEntity(bookListURL, String.class);
        List<Book> bookList = JsonUtils.jsonStrToList(entity.getBody(), Book.class);
        return bookList;
    }
}
