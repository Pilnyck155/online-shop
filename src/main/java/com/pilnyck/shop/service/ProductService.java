package com.pilnyck.shop.service;

import com.pilnyck.shop.dao.ProductDao;
import com.pilnyck.shop.entity.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }
    public List<Product> getAll(){
        List<Product> productList = productDao.getAll();
        return productList;
    }

    public void editProduct(Product newProduct){
        productDao.editById(newProduct);
    }

    public void add(Product product){
        productDao.add(product);
        System.out.println("ProductService add product");
    }

    public Product findById(int id){
        Product product = productDao.findById(id);
        System.out.println("ProductService find by id: " + id);
        return product;
    }

    public void delete(int id){
        productDao.delete(id);
    }
}
