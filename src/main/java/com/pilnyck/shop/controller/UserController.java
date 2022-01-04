package com.pilnyck.shop.controller;

import com.pilnyck.shop.pagegenerator.PageGenerator;
import com.pilnyck.shop.service.SecurityService;
import com.pilnyck.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    protected String getLoginPage() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    protected void checkAuthenticationUser(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        if (securityService.isAuthenticationUser(request)) {
            String userToken = securityService.generateToken();
            System.out.println("user-token: " + userToken);
            Cookie cookie = new Cookie("user-token", userToken);
            resp.addCookie(cookie);
            resp.sendRedirect("/products");
            return;
        } else {
            userService.creationUser(request);
            String userToken = securityService.generateToken();
            Cookie cookie = new Cookie("user-token", userToken);
            resp.addCookie(cookie);
            resp.sendRedirect("/products");
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
