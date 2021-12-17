package com.pilnyck.shop.service;

import com.pilnyck.shop.dao.jdbc.JdbcUserDao;
import com.pilnyck.shop.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.*;

public class SecurityService {
    private List<String> userTokens = Collections.synchronizedList(new ArrayList<>());
    //private UserService userService;

    public String generateToken() {
        String userToken = UUID.randomUUID().toString();
        userTokens.add(userToken);
        return userToken;
    }

    public User hashPasswordGenerator(User user) {
        String sole = generateSole();
        System.out.println("sole: " + sole);
        String password = user.getPassword();
        System.out.println("password: " + password);
        String passwordWithSole = password + sole;
        String hashPassword = DigestUtils.sha256Hex(passwordWithSole);
        System.out.println("hash_password: " + hashPassword);
        user.setPassword(hashPassword);
        user.setSole(sole);
        return user;
    }

    private String generateSole() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytesArray = new byte[20];
        secureRandom.nextBytes(bytesArray);
        String sole = bytesArray.toString();
        return sole;
    }

    //check cookie from userToken list
    public boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user-token")) {
                if (userTokens.contains(cookie.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAuthenticationUser(HttpServletRequest request) {
        User userFromRequest = getUserFromRequest(request);
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        String email = userFromRequest.getEmail();
        User userFromDB = jdbcUserDao.findUserByEmail(email);
        if (userFromDB != null) {
            String sole = userFromDB.getSole();
            String passwordFromDB = userFromDB.getPassword();
            String checkHashPassword = DigestUtils.sha256Hex(userFromRequest.getPassword() + sole);
            if (checkHashPassword.contains(passwordFromDB)) {
                return true;
            }
        }
        return false;
    }

    public User getUserFromRequest(HttpServletRequest request) {
        User user = User.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
        return user;
    }
}

