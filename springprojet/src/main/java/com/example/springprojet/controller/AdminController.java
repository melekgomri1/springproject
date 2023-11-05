package com.example.springprojet.controller;

import com.example.springprojet.model.Category;
import com.example.springprojet.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService;
@GetMapping("/admin/categories")
    public String getCat(Model model){
    model.addAttribute("category",categoryService.getAllCategory());
    return "categories";
}
@GetMapping("/admin/categories/add")
    public String getadd(Model model){
    model.addAttribute("category",new Category());
    return "categoriesAdd";
}
    @PostMapping ("/admin/categories/add")
    public String postadd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
@GetMapping("/admin/categories/delete/{id}")
    public String deletecat(@PathVariable int id){
categoryService.removecategory(id);
    return "redirect:/admin/categories";
}
@GetMapping("/admin/categories/update/{id}")
    public String updatecat(@PathVariable int id,Model model){
    Optional<Category> category=categoryService.getcategorybyid(id);
    if(category.isPresent()){
        model.addAttribute("category",category.get());
        return "categoriesAdd";
    }
    else
        return "404";
}




}
