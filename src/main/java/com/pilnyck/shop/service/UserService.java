package com.pilnyck.shop.service;

import com.pilnyck.shop.dao.UserDao;
import com.pilnyck.shop.entity.Product;
import com.pilnyck.shop.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.util.*;

public class UserService {
    //private Map<String, String> userToken = new HashMap<>();
    //private static final SecureRandom secureRandom;
    private List<String> userToken = new ArrayList<>();
    private UserDao userDao;
    private SecurityService securityService;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(User user) {
        User updateUser = securityService.hashPasswordGenerator(user);
        //String userToken = securityService.generateToken();
        userDao.add(updateUser);
        System.out.println("UserSecurity add user");
    }

    public boolean findUser(User user) {
        boolean isAuth = userDao.findUser(user);
        return isAuth;
    }

    public User findUserByEmail(String email) {
        User user = userDao.findUserByEmail(email);
        return user;
    }



    public void creationUser(HttpServletRequest request){
        User userFromRequest = getUserFromRequest(request);
        add(userFromRequest);
    }
    //boolean isEmailExist


//    public User findUserByToken(Cookies[] cookies) {
//        for (Cookies cookie : cookies) {
//            if (cookie.equals("user-token"))
//        }
//        //User user = userDao.findUserByEmail(email);
//        return user;
//    }

    public User getUserFromRequest(HttpServletRequest request) {
        User user = User.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
        return user;
    }
}