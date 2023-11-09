package com.example.springprojet.controller;

import com.example.springprojet.dto.ProductDTO;
import com.example.springprojet.model.Category;
import com.example.springprojet.model.Product;
import com.example.springprojet.service.CategoryService;
import com.example.springprojet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadir=System.getProperty("user.dir")+"/src/main/resources/static/product";
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
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
//Product Section
@GetMapping("/admin/products")
public String products(Model model){
    model.addAttribute("products",productService.getAllProduct());
    return "product";
}
    @GetMapping("/admin/products/add")
    public String productAdd(Model model){
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productAdd";
    }
    @PostMapping("/admin/products/add")
    public String productaddpost(@ModelAttribute("productDTO")ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName")String imgName) throws IOException{
        Product product= new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getcategorybyid(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        String imageuuid;
        if(!file.isEmpty()){
            imageuuid=file.getOriginalFilename();
            Path filenameandpath= Paths.get(uploadir,imageuuid);
            Files.write(filenameandpath,file.getBytes());
        }else{
            imageuuid=imgName;
        }
        product.setImageName(imageuuid);
        productService.addproduct(product);

    return "redirect:/admin/products";
    }





}
