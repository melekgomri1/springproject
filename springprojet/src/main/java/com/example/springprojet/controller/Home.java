package com.example.springprojet.controller;

import com.example.springprojet.Global.GlobalData;
import com.example.springprojet.service.CategoryService;
import com.example.springprojet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Home {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @GetMapping("/shop")
    public String shop(Model model){
    model.addAttribute("categories",categoryService.getAllCategory());
    model.addAttribute("products",productService.getAllProduct());
    model.addAttribute("cartCount", GlobalData.cart.size());
    return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getallPBC(id));
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewproduct(Model model,@PathVariable int id){
        model.addAttribute("product",productService.getproductbyid(id).get());
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "viewProduct";
    }

}
