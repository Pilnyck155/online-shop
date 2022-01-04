//package com.pilnyck.shop.filter;
//
//import com.pilnyck.shop.service.SecurityService;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//public class SecurityFilter implements Filter {
//
//    private SecurityService securityService;
//
//    public SecurityFilter(SecurityService securityService) {
//        this.securityService = securityService;
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//
//        if (httpServletRequest.getRequestURI().equals("/login")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            System.out.println("Security filter");
//            return;
//        } else if (securityService.checkCookie(httpServletRequest)) {
//            System.out.println("Security filter check cookie");
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            httpServletResponse.sendRedirect("/login");
//        }
//    }
//}
