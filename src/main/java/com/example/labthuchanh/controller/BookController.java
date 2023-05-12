package com.example.labthuchanh.controller;

import com.example.labthuchanh.entity.Book;
import com.example.labthuchanh.entity.Category;
import com.example.labthuchanh.services.BookService;
import com.example.labthuchanh.services.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBook();
        model.addAttribute("books", books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
       if (result.hasErrors()) {
           model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }
        else {
            bookService.saveBook(book);
            return "redirect:/books";
        }
    }
//    @GetMapping("/edit/{id}")
//    public String editBook(@PathVariable("id") Long id, Model model) {
//        Book book = bookService.getBookId(id);
//        model.addAttribute("book", book);
//        model.addAttribute("categories", categoryService.getAllCategories());
//        return "book/edit";
//    }
//    postmapping edit with id

    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book")  Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("selected", book.getCategory().getId());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }
        else {
            bookService.saveBook(book);
            return "redirect:/books";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteBook( @PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id")  Long id, Model model) {
        Book book = bookService.getBookById(id);
        for (Book b : bookService.getAllBook()) {
            if (b.getId().equals(id)) {
                book = b;
            }
        }
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("selected", book.getCategory().getId());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }
        else {
            return "not-found";
        }

    }

}