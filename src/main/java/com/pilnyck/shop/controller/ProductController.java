package com.pilnyck.shop.controller;

import com.pilnyck.shop.entity.Product;
import com.pilnyck.shop.pagegenerator.PageGenerator;
import com.pilnyck.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    protected String showAllProducts(Model model) {
        System.out.println("In Product Service");
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "product_list";
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    protected String getAddProductPage() {
        return "add_product";
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.POST)
    protected String addProduct(HttpServletRequest request) {
        Product product = Product.builder()
                .name(request.getParameter("product_name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .creationDate(Date.valueOf(request.getParameter("date")))
                .build();
        productService.add(product);
        return "redirect:/products";
    }

    @RequestMapping(path = "/products/delete", method = RequestMethod.GET)
    protected String deleteProduct(@RequestParam("id") int id) {
        System.out.println("Product id to delete: " + id);
        productService.delete(id);
        return "redirect:/products";
    }

    @RequestMapping(path = "/products/edit", method = RequestMethod.GET)
    protected String getEditPage(@RequestParam("id") int id, Model model) {
        System.out.println("Product id to edit: " + id);
        Product productToEdit = productService.findById(id);
        model.addAttribute("product", productToEdit);
        ;
        return "edit_product";
    }

    @RequestMapping(path = "/products/edit", method = RequestMethod.POST)
    protected String saveEditProduct(@RequestParam("id") int id, @RequestParam("product_name") String productName,
                                     @RequestParam("price") double price, @RequestParam("date") Date date){
        System.out.println("In save edit products method");
        Product productToEdit = Product.builder()
                .id(id)
                .name(productName)
                .price(price)
                .creationDate(date)
                .build();
        productService.editProduct(productToEdit);
        return "redirect:/products";
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}