package com.example.controller;

import com.example.config.BookProperties;
import com.example.model.Book;
import com.example.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookProperties properties;

    @GetMapping
    public List<Book> books() {
        log.info("getting all the book");
        return this.bookService.findAll();
    }

    @GetMapping("{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return this.bookService.findByIsbn(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody Book book) {
        return this.bookService.save(book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        this.bookService.deleteByIsbn(isbn);
    }

    @PutMapping("{isbn}")
    public Book update(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return this.bookService.editBook(isbn, book);
    }

    @GetMapping("properties")
    public String getProperties() {
        return this.properties.getGreeting();
    }

}
