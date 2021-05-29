package com.example.service;

import com.example.model.Book;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Book findByIsbn(String isbn);

    Book save(Book book);

    void deleteByIsbn(String isbn);

    Book editBook(String isbn,Book book);
}
