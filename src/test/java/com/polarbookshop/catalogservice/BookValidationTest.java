package com.polarbookshop.catalogservice;


import com.polarbookshop.catalogservice.domain.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class BookValidationTest {


    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void when_field_are_correct() {
        var book = new Book("1234567890", "Title", "Author", 9.9);
        Set<ConstraintViolation<Book>> violation = validator.validate(book);
        assertThat(violation).isEmpty();
    }

    @Test
    void when_field_are_not_correct() {
        var book = new Book("a1234567890", "Title", "Author", 9.9);
        Set<ConstraintViolation<Book>> violation = validator.validate(book);
        assertThat(violation).hasSize(1);
        assertThat(violation.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");

    }
}
