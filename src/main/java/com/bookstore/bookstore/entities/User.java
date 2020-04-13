package com.bookstore.bookstore.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Column(name = "email", nullable = false, updatable = false)
    private String email;
    private String contact_number;
    private boolean enabled = true;

    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String email, String contact_number, boolean enabled) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact_number = contact_number;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new org.apache.commons.lang.builder.EqualsBuilder()
                .append(enabled, user.enabled)
                .append(id, user.id)
                .append(username, user.username)
                .append(password, user.password)
                .append(firstName, user.firstName)
                .append(lastName, user.lastName)
                .append(email, user.email)
                .append(contact_number, user.contact_number)
                .append(createdOn, user.createdOn)
                .append(updatedOn, user.updatedOn)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang.builder.HashCodeBuilder(17, 37)
                .append(id)
                .append(username)
                .append(password)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(contact_number)
                .append(enabled)
                .append(createdOn)
                .append(updatedOn)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("password", password)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("contact_number", contact_number)
                .append("enabled", enabled)
                .append("createdOn", createdOn)
                .append("updatedOn", updatedOn)
                .toString();
    }
}
