package com.example.labthuchanh.services;

import com.example.labthuchanh.entity.Book;
import com.example.labthuchanh.entity.Category;
import com.example.labthuchanh.repository.IBookRepository;
import com.example.labthuchanh.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }
    public void saveCategory(Category category){
        categoryRepository.save(category);
    }
    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
}
