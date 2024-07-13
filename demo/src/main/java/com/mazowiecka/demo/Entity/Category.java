package com.mazowiecka.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Categories")
//@Table(name = "test_categories")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }

    public Category() {
    }
}
