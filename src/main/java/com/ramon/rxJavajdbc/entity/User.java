package com.ramon.rxJavajdbc.entity;

import com.github.davidmoten.rx.jdbc.annotations.Column;

public interface User {
    @Column("id")
    int id();
    @Column("name")
    String name();
}
