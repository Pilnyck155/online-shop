package com.pilnyck.shop.dao.jdbc;

import com.pilnyck.shop.dao.ProductDao;
import com.pilnyck.shop.dao.UserDao;
import com.pilnyck.shop.dao.jdbc.mapper.ProductRowMapper;
import com.pilnyck.shop.dao.jdbc.mapper.UserRowMapper;
import com.pilnyck.shop.entity.Product;
import com.pilnyck.shop.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcUserDao implements UserDao {

    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    public static final String CREATE_USER_TABLE = """
            CREATE TABLE Users (
                email VARCHAR (30) PRIMARY KEY NOT NULL,
                h_password VARCHAR (50) NOT NULL
                sole VARCHAR (20) NOT NULL);""";
    public static final String INSERT_USER = """
            INSERT INTO Users (email, h_password, sole)
            VALUES (?, ?, ?);""";
    private static final String FIND_USER_BY_EMAIL = "SELECT email, h_password, sole FROM Users WHERE email = ?;";

    private Connection getConnection() throws SQLException {
        //return DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "postgres");
        return DriverManager.getConnection("jdbc:postgresql://localhost:3000/shop", "postgres", "password");
    }
/*
    public void createTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(CREATE_USER_TABLE);
            System.out.println("User table is created");
        } catch (SQLException exception) {
            throw new RuntimeException("Error with user table creation", exception);
        }
    }

 */

    @Override
    public void add(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSole());
            preparedStatement.executeUpdate();
            System.out.println("End addUser method");
        } catch (SQLException exception) {
            throw new RuntimeException("Error with add user", exception);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        User user = null;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    user = USER_ROW_MAPPER.mapRow(resultSet);
                }
                System.out.println("End findUserByEmail method");
                return user;
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Error with find user by email", exception);
        }
    }
}
