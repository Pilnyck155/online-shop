package com.pilnyck.shop.dao.jdbc.mapper;

import com.pilnyck.shop.entity.Product;
import com.pilnyck.shop.entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

//    email VARCHAR (30) PRIMARY KEY NOT NULL,
//            hash_password VARCHAR (30) NOT NULL);

public class UserRowMapper {
    public User mapRow(ResultSet resultSet) throws SQLException {
        String email = resultSet.getString("email");
        String hPassword = resultSet.getString("h_password");
        String sole= resultSet.getString("sole");
        //String cookie = resultSet.getString("cookie");
        User user = User.builder()
                .email(email)
                .password(hPassword)
                .sole(sole)
                .build();
        return user;
    }
}
