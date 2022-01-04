package com.pilnyck.shop.service;

import com.pilnyck.shop.dao.UserDao;
import com.pilnyck.shop.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class UserService {
    private List<String> userToken = new ArrayList<>();
    private UserDao userDao;
    private SecurityService securityService;

    public UserService(UserDao userDao, SecurityService securityService) {
        this.userDao = userDao;
        this.securityService = securityService;
    }

    public void add(User user) {
        User updateUser = securityService.hashPasswordGenerator(user);
        userDao.add(updateUser);
        System.out.println("UserSecurity add user");
    }

    public User findUserByEmail(String email) {
        User user = userDao.findUserByEmail(email);
        return user;
    }


    public void creationUser(HttpServletRequest request) {
        User userFromRequest = getUserFromRequest(request);
        add(userFromRequest);
    }

    public User getUserFromRequest(HttpServletRequest request) {
        User user = User.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
        return user;
    }
}