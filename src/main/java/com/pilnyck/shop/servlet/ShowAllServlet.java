package com.pilnyck.shop.servlet;

import com.pilnyck.shop.entity.Product;
import com.pilnyck.shop.pagegenerator.PageGenerator;
import com.pilnyck.shop.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ShowAllServlet extends HttpServlet {
    private ProductService productService;

    public ShowAllServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.getAll();
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products", products);
        String page = pageGenerator.getPage("product_list.html", parameters);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(page);
    }
}
