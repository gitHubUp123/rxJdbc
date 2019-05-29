package com.ramon.rxJavajdbc.entity;

import lombok.Data;

@Data
public class User2 {
    private int id;
    private String name;

    public User2(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
