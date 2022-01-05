package com.pilnyck.shop.dao.jdbc;

import com.pilnyck.shop.dao.ProductDao;
import com.pilnyck.shop.dao.jdbc.mapper.ProductRowMapper;
import com.pilnyck.shop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcProductDao implements ProductDao {

    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    public static final String INSERT_PRODUCT = """
            INSERT INTO Products (name, price, date)
            VALUES (?, ?, ?);""";
    private static final String SELECT_ALL_PRODUCTS = "SELECT product_id, name, price, date FROM Products ORDER BY product_id;";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT product_id, name, price, date FROM Products WHERE product_id = ?;";
    public static final String UPDATE_PRODUCT = """
            UPDATE Products
            SET name = ?, price = ?, date = ?
            WHERE product_id = ?;""";
    public static final String DELETE_PRODUCT = """
            DELETE FROM Products
            WHERE product_id = ?;""";


    @Override
    public List<Product> getAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                productList.add(product);
            }
            return productList;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
            }
            return product;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void editById(Product newProduct) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);) {
            preparedStatement.setString(1, newProduct.getName());
            preparedStatement.setDouble(2, newProduct.getPrice());
            preparedStatement.setDate(3, newProduct.getCreationDate());
            preparedStatement.setInt(4, newProduct.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Error with edit newProduct", exception);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Error with delete product", exception);
        }
    }


    @Override
    public void add(Product product) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDate(3, product.getCreationDate());
            preparedStatement.executeUpdate();
            System.out.println("End saveProduct method");
        } catch (SQLException e) {
            throw new RuntimeException("Error with product add", e);
        }
    }

    private Connection getConnection() throws SQLException {
        //return DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "postgres");
        return DriverManager.getConnection("jdbc:postgresql://localhost:3000/shop", "postgres", "password");
    }
}
