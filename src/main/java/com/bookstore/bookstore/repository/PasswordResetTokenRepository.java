package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.domain.User;
import com.bookstore.bookstore.domain.security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

    Stream<PasswordResetToken> findAllByExpiryTimeLessThan(Date now);

   /* @Modifying
    @Query("DELETE FROM PasswordResetToken t WHERE t.expiryTime <= ? 1")
    void deleteAllExpiredSince(Date now);*/
}
