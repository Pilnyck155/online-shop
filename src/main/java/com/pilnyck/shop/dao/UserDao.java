package com.pilnyck.shop.dao;

import com.pilnyck.shop.entity.User;

public interface UserDao {
    void add(User user);
    User findUserByEmail(String email);
}
