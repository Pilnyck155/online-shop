//package com.pilnyck.shop.servlet;
//
//import com.pilnyck.shop.pagegenerator.PageGenerator;
//import com.pilnyck.shop.service.SecurityService;
//import com.pilnyck.shop.service.UserService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//public class LoginServlet extends HttpServlet {
//    private UserService userService;
//    private SecurityService securityService;
//
//    public LoginServlet(UserService userService, SecurityService securityService) {
//        this.userService = userService;
//        this.securityService = securityService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PageGenerator pageGenerator = PageGenerator.instance();
//        String page = pageGenerator.getPage("login.html");
//        response.getWriter().println(page);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
//        if (securityService.isAuthenticationUser(request)) {
//            String userToken = securityService.generateToken();
//            System.out.println("user-token: " + userToken);
//            Cookie cookie = new Cookie("user-token", userToken);
//            resp.addCookie(cookie);
//            resp.sendRedirect("/");
//            return;
//        } else {
//            userService.creationUser(request);
//            String userToken = securityService.generateToken();
//            Cookie cookie = new Cookie("user-token", userToken);
//            resp.addCookie(cookie);
//            resp.sendRedirect("/");
//        }
//    }
//}
