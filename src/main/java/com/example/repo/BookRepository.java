package com.example.repo;

import com.example.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface BookRepository extends CrudRepository<BookEntity, Long> {

    Optional<BookEntity> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    @Transactional
    void deleteByIsbn(String isbn);
}
