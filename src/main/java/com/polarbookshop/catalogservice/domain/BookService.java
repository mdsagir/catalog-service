package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final JpaBookRepository jpaBookRepository;

    public BookService(JpaBookRepository jpaBookRepository) {
        this.jpaBookRepository = jpaBookRepository;
    }


    public Iterable<Book> viewBookList() {
        return jpaBookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return jpaBookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        String isbn = book.isbn();
        var b = jpaBookRepository.existsByIsbn(isbn);
        if (b) {
            throw new BookAlreadyExistsException(isbn);
        }
        return jpaBookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        jpaBookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return jpaBookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var bookToUpdate = new Book(
                            existingBook.id(),
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.version());
                    return jpaBookRepository.save(bookToUpdate);
                })
                .orElseGet(() -> addBookToCatalog(book));
    }

}
