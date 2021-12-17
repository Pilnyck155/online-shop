package com.pilnyck.shop.servlet;

import com.pilnyck.shop.entity.Product;
import com.pilnyck.shop.entity.User;
import com.pilnyck.shop.pagegenerator.PageGenerator;
import com.pilnyck.shop.service.ProductService;
import com.pilnyck.shop.service.SecurityService;
import com.pilnyck.shop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private UserService userService;
    private SecurityService securityService;

    public LoginServlet(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }
    //private List<String> userToken;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("login.html");
        response.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        if (securityService.isAuthenticationUser(request)){
            String userToken = securityService.generateToken();
            System.out.println("user-token: " + userToken);
            Cookie cookie = new Cookie("user-token", userToken);
            resp.addCookie(cookie);
            //resp.addCookie(new Cookie("preferred-language", "ua"));
            resp.sendRedirect("/");
        }else {
            userService.creationUser(request);
            String userToken = securityService.generateToken();
            Cookie cookie = new Cookie("user-token", userToken);
            resp.addCookie(cookie);
            resp.sendRedirect("/");
        }
//        User userFromRequest = userService.getUserFromRequest(request);
//        userService.add(userFromRequest);
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");

        //перевірка на наявність токена користувача в userToken (authentication)
        // якщо ні!
        //генерація sole та password і додавання User в БД (authorization)



        //звернення до SecurityService
        //System.out.println(email + " : " + password);

        //String userToken = UUID.randomUUID().toString();

        //System.out.println("User token: " + userToken);


    }
}
