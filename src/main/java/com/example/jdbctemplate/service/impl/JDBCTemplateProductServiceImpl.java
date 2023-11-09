package com.example.jdbctemplate.service.impl;

import com.example.jdbctemplate.entity.Product;
import com.example.jdbctemplate.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class JDBCTemplateProductServiceImpl implements ProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getDouble("price"),
            rs.getInt("quantity")
    );
    @Override
    public List<Product> findAll() {
        var sql = "SELECT * from product";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        var sql = "SELECT * FROM product WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public void create(Product product) {
        var sql = "insert into product (id,name,price,quantity) values (?,?,?,?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getQuantity());

    }

    @Override
    public void update(Product product, Integer id) {
     var sql = "update product set name = ?,price = ?,quantity =? where id =?";
     jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getQuantity(), id);
    }

    @Override
    public void delete(Integer id) {
        var sql = "delete from product where id = ?";
        jdbcTemplate.update(sql,id);
    }
}
