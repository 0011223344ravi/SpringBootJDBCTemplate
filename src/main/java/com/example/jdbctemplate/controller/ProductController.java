package com.example.jdbctemplate.controller;

import com.example.jdbctemplate.entity.Product;
import com.example.jdbctemplate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService  productService;

    @GetMapping("/getAllProducts")
    List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/findById/{id}")
    Optional<Product> findProductById(@PathVariable Integer id ) {
        return productService.findById(id);
    }

    @PostMapping("/insert")
    public void insertProduct(@RequestBody Product product){
        productService.create(product);
    }
    @PutMapping("/update/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable Integer id ){
        productService.update(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id ){

        productService.delete(id);
     }
}
