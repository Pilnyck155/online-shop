package com.pilnyck.shop.dao.jdbc.mapper;

import com.pilnyck.shop.entity.Product;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper {
    public Product mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        Date date = resultSet.getDate("date");
        Product product = Product.builder().
                id(id)
                .name(name)
                .price(price)
                .creationDate(date)
                .build();
        return product;
    }
}
