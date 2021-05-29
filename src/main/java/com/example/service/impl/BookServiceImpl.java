package com.example.service.impl;

import com.example.entity.BookEntity;
import com.example.exception.BookAlreadyExistsException;
import com.example.exception.BookNotFoundException;
import com.example.model.Book;
import com.example.repo.BookRepository;
import com.example.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        Iterable<BookEntity> bookEntities = bookRepository.findAll();
        Stream<BookEntity> bookEntityStream = StreamSupport.stream(bookEntities.spliterator(), false);
        return bookEntityStream.map(bookEntity ->
                new Book(bookEntity.getIsbn(), bookEntity.getTitle(), bookEntity.getAuthor(), Year.of(bookEntity.getPublishingYear()), bookEntity.getPrice()))
                .collect(Collectors.toList());
    }


    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookEntity ->
                        new Book(bookEntity.getIsbn(), bookEntity.getTitle(), bookEntity.getAuthor(), Year.of(bookEntity.getPublishingYear()), bookEntity.getPrice()))
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @Override
    public Book save(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException(book.getIsbn());
        }
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setPublishingYear(book.getPublishingYear().getValue());
        bookEntity.setPrice(book.getPrice());
        bookRepository.save(bookEntity);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        if (!bookRepository.existsByIsbn(isbn)) {
            throw new BookNotFoundException(isbn);
        }
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    public Book editBook(String isbn, Book book) {

        Optional<BookEntity> existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook.isEmpty()) {
            return save(book);
        }
        BookEntity bookToUpdate = existingBook.get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setPublishingYear(book.getPublishingYear().getValue());
        bookToUpdate.setPrice(book.getPrice());
        bookRepository.save(bookToUpdate);
        return book;
    }
}
