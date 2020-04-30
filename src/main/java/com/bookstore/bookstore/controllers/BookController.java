package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.domain.Book;
import com.bookstore.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/bookShelf")
    public String bookShelf(Model model){
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList",bookList);
        return "bookShelf";
    }
}
