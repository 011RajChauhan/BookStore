package com.bookstore.bookstore;

import com.bookstore.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       /* User user = new User();

        user.setFirstName("Rajan");
        user.setLastName("Chauhan");
        user.setEmail("011rajchauhan@gmail.com");
        user.setUsername("011rajchauhan");
        user.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        Set<UserRole> userRoles = new HashSet<>();
        Role role = new Role();
        role.setName("USER_ROLE");
        role.setRoleId(1);
        userRoles.add(new UserRole(user,role));

        userService.createUser(user,userRoles);*/
    }
}
