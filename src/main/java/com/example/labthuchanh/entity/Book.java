package com.example.labthuchanh.entity;

import com.example.labthuchanh.validator.annotation.ValidCategoryId;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    @NotEmpty(message = "Title must not be empty")
    @Size(min = 2, max = 30, message = "Title must be between 2 and 30 characters")
    private String title;
    @Column(name = "author")
    @NotEmpty(message = "Author must not be null")
    private String author;
    @Column(name = "price")
    @NotNull(message = "Price must not be null")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private Category category;
}
