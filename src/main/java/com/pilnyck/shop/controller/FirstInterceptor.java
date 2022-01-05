package com.pilnyck.shop.controller;

import com.pilnyck.shop.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstInterceptor implements HandlerInterceptor {

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("In preHandle method");
        if (request.getRequestURI().equals("/login")) {
            System.out.println("Security filter");
            return true;
        } else if (securityService.checkCookie(request)) {
            System.out.println("Security filter check cookie");
            return true;
        } else {
            response.sendRedirect("/login");
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("In postHandle method");
        response.setContentType("text/html;charset=utf-8");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("In afterCompletion method");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
