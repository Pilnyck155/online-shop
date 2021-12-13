package com.pilnyck.shop.servlet;

import com.pilnyck.shop.entity.Product;
import com.pilnyck.shop.pagegenerator.PageGenerator;
import com.pilnyck.shop.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class DeleteProductsServlet extends HttpServlet {
    private ProductService productService;

    public DeleteProductsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Product id: " + id);
        productService.delete(id);
        response.sendRedirect("/products");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product productToEdit = getProductFromRequest(request);
            productService.editProduct(productToEdit);
            response.sendRedirect("/products");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private Product getProductFromRequest(HttpServletRequest request) {
        Product product = Product.builder()
                .id(Integer.parseInt(request.getParameter("id")))
                .name(request.getParameter("product_name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .creationDate(Date.valueOf(request.getParameter("date")))
                .build();
        return product;
    }
}
