package com.bookstore.bookstore.service;


import com.bookstore.bookstore.domain.Book;

import java.util.List;

public interface BookService {

    public List<Book> findAll();

    public Book findBookById(Long id);
}
