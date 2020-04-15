package com.bookstore.bookstore.service.impl;

import com.bookstore.bookstore.domain.User;
import com.bookstore.bookstore.domain.security.PasswordResetToken;
import com.bookstore.bookstore.domain.security.UserRole;
import com.bookstore.bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.bookstore.repository.RoleRepository;
import com.bookstore.bookstore.repository.UserRepository;
import com.bookstore.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        final PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
       User newUser =  userRepository.findByUsername(user.getUsername());
       if(newUser!=null){
           throw new Exception("User Already Exists, nothing will be done.");
       }else{
           for(UserRole ur: userRoles) {
               roleRepository.save(ur.getRole());
           }
           user.getUserRoles().addAll(userRoles);
           newUser = userRepository.save(user);
       }
       return newUser;
    }
}
