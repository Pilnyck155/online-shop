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

public class EditProductsServlet extends HttpServlet {
    private ProductService productService;

    public EditProductsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Product id: " + id);

        Product productToEdit = productService.findById(id);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("product", productToEdit);
        String page = pageGenerator.getPage("edit_product.html", parameters);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        try {
            Product productToEdit = getProductFromRequest(request);
            productService.editProduct(productToEdit);

            response.sendRedirect("/products");
        } catch (Exception exception) {
            String errorMessage = "Your product wasn't edit! Try later!";
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = pageGenerator.getPage("edit_product.html", parameters);
            response.getWriter().println(page);
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
