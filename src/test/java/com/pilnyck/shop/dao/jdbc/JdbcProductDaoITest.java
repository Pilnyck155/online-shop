package com.pilnyck.shop.dao.jdbc;

import com.pilnyck.shop.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

//import static org.junit.jupiter.api.AssertFalse.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

class JdbcProductDaoITest {
    @Test
    public void testGetAllReturnCorrectData() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        List<Product> allProducts = jdbcProductDao.getAll();
        assertFalse(allProducts.isEmpty());
        for (Product product : allProducts) {
            assertNotEquals(0, product.getId());
            assertNotNull(product.getName());
            assertNotNull(product.getPrice());
            assertNotNull(product.getCreationDate());
        }
    }
}