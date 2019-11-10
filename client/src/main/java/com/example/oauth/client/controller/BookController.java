package com.example.oauth.client.controller;

import com.example.oauth.client.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "books";
    }

    @RequestMapping("/callback")
    public String callback(@RequestParam String code) {
        System.out.println(code);
        // TODO
        return "books";
    }
}
