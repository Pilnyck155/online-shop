package com.pilnyck.shop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@ToString
@Getter
@Setter
@Builder
public class Product {
    private int id;
    private String name;
    private double price;
    private Date creationDate;
}
