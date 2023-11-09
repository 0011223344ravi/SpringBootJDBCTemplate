package com.example.jdbctemplate.service;

import com.example.jdbctemplate.entity.Product;
import org.springframework.data.relational.core.sql.In;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Integer id);

    void create(Product product);

    void update(Product product, Integer id);

    void delete(Integer id);
}
