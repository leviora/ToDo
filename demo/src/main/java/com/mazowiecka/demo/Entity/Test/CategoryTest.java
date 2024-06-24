package com.mazowiecka.demo.Entity.Test;

import com.mazowiecka.demo.Entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    public void testSetAndGetCategoryId() {
        Long id = 1L;
        category.setCategoryId(id);
        assertEquals(id, category.getCategoryId());
    }

    @Test
    public void testSetAndGetCategoryName() {
        String name = "Test Category";
        category.setCategoryName(name);
        assertEquals(name, category.getCategoryName());
    }

    @Test
    public void testToString() {
        String name = "Test Category";
        category.setCategoryName(name);
        assertEquals("Category{" + "categoryName='" + name + '\'' + '}', category.toString());
    }
}
