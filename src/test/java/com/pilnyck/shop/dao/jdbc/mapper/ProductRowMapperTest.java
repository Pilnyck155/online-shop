package com.pilnyck.shop.dao.jdbc.mapper;

import com.pilnyck.shop.entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProductRowMapperTest {
    @Test
    public void testMapRowWorkCorrect() throws SQLException {
        // prepare
        ProductRowMapper productRowMapper = new ProductRowMapper();
        Date createDate = new Date(2021, 12, 01);
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getInt("product_id")).thenReturn(1);
        when(resultSetMock.getString("name")).thenReturn("milk");
        when(resultSetMock.getDouble("price")).thenReturn(17.0);
        when(resultSetMock.getDate("date")).thenReturn(createDate);

        //when
        Product actualProducts = productRowMapper.mapRow(resultSetMock);

        //then
        assertEquals(1, actualProducts.getId());
        assertEquals("milk", actualProducts.getName());
        assertEquals(17.0, actualProducts.getPrice());
        assertEquals(createDate, actualProducts.getCreationDate());
    }
}