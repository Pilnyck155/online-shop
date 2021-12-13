package com.pilnyck.shop.dao;

import com.pilnyck.shop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAll();
    Product findById(int id);
    void add(Product product);
    void editById(Product product);
    void delete(int id);
}
