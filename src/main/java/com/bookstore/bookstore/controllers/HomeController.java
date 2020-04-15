package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.domain.User;
import com.bookstore.bookstore.domain.security.PasswordResetToken;
import com.bookstore.bookstore.domain.security.Role;
import com.bookstore.bookstore.domain.security.UserRole;
import com.bookstore.bookstore.service.UserService;
import com.bookstore.bookstore.service.impl.UserSecurityService;
import com.bookstore.bookstore.utility.MailConstructor;
import com.bookstore.bookstore.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Controller

public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

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
        if (passwordResetToken == null) {
            model.addAttribute("message", "Invalid Token");
            return "redirect:/badRequest";
        }

        User user = passwordResetToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        /*creating current log in session for the user found
         * ensures that current session is for the logged in user*/
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("newUserFormAndTabActive", true);
        return "myProfile";
    }

    @PostMapping("/registerNewUser")
    public String registerNewUser(HttpServletRequest request,
                                  Model model,
                                  @ModelAttribute("email") String email,
                                  @ModelAttribute("username") String username) throws Exception {
        model.addAttribute("loginFormAndTabActive", true);
        model.addAttribute("email", email);
        model.addAttribute("username", username);

        if (userService.findByUsername(username) != null) {
            model.addAttribute("usernameExists", true);
            return "myAccount";
        }
        if (userService.findByEmail(email) != null) {
            model.addAttribute("emailExists", true);
            return "myAccount";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));

        userService.createUser(user, userRoles);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String appUrl =
                "http://" +
                        request.getServerName() +
                        ":" +
                        request.getServerPort() +
                        request.getContextPath();

        SimpleMailMessage passwordResetMail = mailConstructor.constructResetPasswordEmail(appUrl, request.getLocale(),
                token, user, password);
        mailSender.send(passwordResetMail);

        model.addAttribute("emailSent", true);
        return "myAccount";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        model.addAttribute("forgotPasswordFormAndTabActive", true);
        return "myAccount";
    }
}
