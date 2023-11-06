package com.example.springprojet.service;

import com.example.springprojet.model.Product;
import com.example.springprojet.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProduct(){return productRepository.findAll();}
}
