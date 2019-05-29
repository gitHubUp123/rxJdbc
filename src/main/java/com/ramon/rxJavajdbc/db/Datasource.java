package com.ramon.rxJavajdbc.db;

import com.github.davidmoten.rx.jdbc.ConnectionProvider;
import com.github.davidmoten.rx.jdbc.ConnectionProviderFromUrl;

public class Datasource {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123";
    public static ConnectionProvider connectionProvider
            = new ConnectionProviderFromUrl(
            DB_CONNECTION, DB_USER, DB_PASSWORD);
}
