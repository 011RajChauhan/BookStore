package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.domain.User;
import com.bookstore.bookstore.domain.security.PasswordResetToken;
import com.bookstore.bookstore.service.UserService;
import com.bookstore.bookstore.service.impl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller

public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

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
    public String register(Model model, Locale locale, @RequestParam("token") String token) {
        PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);
        if(passwordResetToken == null){
            model.addAttribute("message","Invalid Token");
            return "redirect:/badRequest";
        }

        User user = passwordResetToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        /*creating current log in session for the user found
        * ensures that current session is for the logged in user*/
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,userDetails.getPassword(),userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("newUserFormAndTabActive", true);
        return "myProfile";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        model.addAttribute("forgotPasswordFormAndTabActive", true);
        return "myAccount";
    }
}
