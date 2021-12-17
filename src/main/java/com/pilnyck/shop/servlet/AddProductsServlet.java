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

import java.util.List;
import java.util.Map;

public class AddProductsServlet extends HttpServlet {

    private ProductService productService;

    public AddProductsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("add_product.html");
        response.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();

        try{
            Product product = getProductFromRequest(request);
            productService.add(product);
            String page = pageGenerator.getPage("add_product.html");
            response.getWriter().println(page);
            response.sendRedirect("/products");
        } catch (Exception exception){
            String errorMessage = "Your product wasn't add! Try later!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = pageGenerator.getPage("add_product.html", parameters);
            response.getWriter().println(page);

            exception.printStackTrace();
        }
    }

    private Product getProductFromRequest(HttpServletRequest request) {
        Product product = Product.builder()
                .name(request.getParameter("product_name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .creationDate(Date.valueOf(request.getParameter("date")))
                .build();
        return product;
    }
}
