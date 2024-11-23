package com.mazowiecka.demo;

import com.mazowiecka.demo.Service.DatabaseConnectionChecker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@EntityScan(basePackages = "com.mazowiecka.demo.Entity")
public class ToDoListApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ToDoListApplication.class, args);
        DatabaseConnectionChecker connectionChecker = context.getBean(DatabaseConnectionChecker.class);

        connectionChecker.checkDatabaseConnection();
    }

}
