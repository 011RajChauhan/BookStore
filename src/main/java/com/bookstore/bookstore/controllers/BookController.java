package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.domain.Book;
import com.bookstore.bookstore.domain.User;
import com.bookstore.bookstore.service.BookService;
import com.bookstore.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @GetMapping("/bookShelf")
    public String bookShelf(Model model) {
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);
        return "bookShelf";
    }

    @GetMapping("/bookDetail")
    public String bookDetail(@PathParam("id") Long id, Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "bookDetail";
    }
}
