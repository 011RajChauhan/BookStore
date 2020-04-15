package com.bookstore.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginFormAndTabActive", true);
        return "myAccount";
    }

    @GetMapping("/registerNewUser")
    public String register(Model model) {
        model.addAttribute("newUserFormAndTabActive", true);
        return "myAccount";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        model.addAttribute("forgotPasswordFormAndTabActive", true);
        return "myAccount";
    }
}
