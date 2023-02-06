package com.polarbookshop.catalogservice.domain;


import com.polarbookshop.catalogservice.domain.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        var book = Book.of("1234567890", "Title", "Author", 9.9,"Publisher");
        Set<ConstraintViolation<Book>> violation = validator.validate(book);
        assertThat(violation).isEmpty();
    }

    @Test
    void when_field_are_not_correct() {
        var book = Book.of("a1234567890", "Title", "Author", 9.9,"Publisher");
        Set<ConstraintViolation<Book>> violation = validator.validate(book);
        assertThat(violation).hasSize(1);
        assertThat(violation.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");

    }

    @Test
    void when_isin_not_define_validation_fails() {
        var book = Book.of("", "Title", "Author", 9.9,"Publisher");
        Set<ConstraintViolation<Book>> violation = validator.validate(book);
        assertThat(violation).hasSize(2);
        List<String> constraintViolationMessages = violation.stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertThat(constraintViolationMessages)
                .contains("The book ISBN must be defined.")
                .contains("The ISBN format must be valid.");

    }
    @Test
    void when_title_is_not_defined_then_validation_fails() {
        var book = Book.of("1234567890", "", "Author", 9.90,"Publisher");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book title must be defined.");
    }
    @Test
    void when_author_is_not_defined_then_validation_fails() {
        var book = Book.of("1234567890", "Title", "", 9.90,"Publisher");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book author must be defined.");
    }
    @Test
    void when_price_is_not_defined_then_validation_fails() {
        var book = Book.of("1234567890", "Title", "Author", null,"Publisher");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be defined.");
    }
    @Test
    void when_price_is_defined_but_zero_then_validation_fails() {
        var book = Book.of("1234567890", "Title", "Author", 0.0,"Publisher");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be greater than zero.");
    }
    @Test
    void when_price_defined_but_negative_then_validation_fails() {
        var book = Book.of("1234567890", "Title", "Author", -9.90,"Publisher");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The book price must be greater than zero.");
    }
}
