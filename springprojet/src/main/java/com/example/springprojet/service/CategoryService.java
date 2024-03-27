package com.example.springprojet.service;

import com.example.springprojet.model.Category;
import com.example.springprojet.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
@Autowired
    CategoryRepository categoryRepository;
public List<Category> getAllCategory(){
    return categoryRepository.findAll();
}


public void addCategory(Category category){
    categoryRepository.save(category);
}
public void removecategory(int id){
    categoryRepository.deleteById(id);
}
public Optional<Category> getcategorybyid(int id){return categoryRepository.findById(id);}










}
