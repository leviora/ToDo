package com.mazowiecka.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseConnectionChecker {
    private final DataSource dataSource;

    @Autowired
    public DatabaseConnectionChecker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void checkDatabaseConnection() {
        try {
            Connection connection = DataSourceUtils.getConnection(dataSource);

            if (connection != null && !connection.isClosed())

                DataSourceUtils.releaseConnection(connection, dataSource);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
