package com.polarbookshop.catalogservice.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface JpaBookRepository extends CrudRepository<Book,Long> {

    Optional<Book> findByIsbn(String isbn);

    @Modifying
    @Transactional
    @Query("DELETE from Book where isbn=:isbn")
    void deleteByIsbn(String isbn);

    boolean existsByIsbn(String isin);
}
