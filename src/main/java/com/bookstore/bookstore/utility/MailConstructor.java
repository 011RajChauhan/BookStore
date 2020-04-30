package com.bookstore.bookstore.utility;

import com.bookstore.bookstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MailConstructor {

    @Autowired
    private Environment env;

    public SimpleMailMessage constructResetPasswordEmail(
            String contextPath,
            String token,
            User user) {

        System.out.println("contextPath is -->");
        System.out.println(contextPath);
        String url = contextPath + "/registerNewUser?token=" + token;
        String message =
                "\nPlease Click on this link to verify your email.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Raj's Bookstore -- New User");
        email.setText(url + message);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }
}
