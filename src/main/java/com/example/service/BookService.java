package com.example.service;

import com.example.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findByIsbn(String isbn);

    Book save(Book book);

    void deleteByIsbn(String isbn);

    Book editBook(String isbn, Book book);
}
