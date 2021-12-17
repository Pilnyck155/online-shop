package com.pilnyck.shop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Builder
public class User {
    private String email;
    private String password;
    private String sole;
}
