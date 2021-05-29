package com.example.entity;

import com.example.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Year;

@Entity
@Data
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BookEntity extends BaseEntity {

    private String isbn;
    private String title;
    private String author;
    private Integer publishingYear;
    private Double price;
}
