package com.bookstore.bookstore.service.impl;

import com.bookstore.bookstore.domain.User;
import com.bookstore.bookstore.domain.security.PasswordResetToken;
import com.bookstore.bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        final PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }
}
