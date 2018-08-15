package com.itheima.thymeleaf.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class User {
    private String username;

    private String password;

    private String sex;

}
