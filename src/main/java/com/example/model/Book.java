package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class Book {


    @NotBlank(message = "The book ISBN must be defined.")
    private String isbn;

    @NotBlank(message = "The book title must be defined.")
    private String title;

    @NotBlank(message = "The book author must be defined.")
    private String author;

    private Integer publishingYear;

    @NotNull(message = "The book price must be defined.")
    @Positive(message = "The book price must be greater than zero.")
    private Double price;
}
