package com.pilnyck.shop.dao.jdbc.mapper;

import com.pilnyck.shop.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRowMapperTest {

    @Test
    public void testUserMapRow() throws SQLException {
        //prepare
        UserRowMapper userRowMapper = new UserRowMapper();
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("email")).thenReturn("admin@gmail.com");
        when(resultSetMock.getString("h_password")).thenReturn("0a83a40219dd7cb8ef33268bfd3eadf3183475b02735da4b3dc7d1ab9f95c6f3");
        when(resultSetMock.getString("sole")).thenReturn("[B@1c89c738");

        //when
        User actualUser = userRowMapper.mapRow(resultSetMock);

        //then
        assertEquals("admin@gmail.com", actualUser.getEmail());
        assertEquals("0a83a40219dd7cb8ef33268bfd3eadf3183475b02735da4b3dc7d1ab9f95c6f3", actualUser.getPassword());
        assertEquals("[B@1c89c738", actualUser.getSole());
    }
}