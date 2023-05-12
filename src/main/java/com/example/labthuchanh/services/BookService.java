package com.example.labthuchanh.services;

import com.example.labthuchanh.entity.Book;
import com.example.labthuchanh.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private IBookRepository bookRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }
    public Book getBookById(Long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }
    public void saveBook(Book book){
        bookRepository.save(book);
    }
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }
}
