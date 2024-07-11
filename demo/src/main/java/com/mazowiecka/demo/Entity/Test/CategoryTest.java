//package com.mazowiecka.demo.Entity.Test;
//
//import com.mazowiecka.demo.Entity.Category;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import javax.persistence.EntityManager;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//public class CategoryTest {
//    @Autowired
//    private EntityManager entityManager;
//
//    private Category category;
//
//    @BeforeEach
//    public void setUp() {
//        category = new Category();
//    }
//
//    @Test
//    public void testSetAndGetCategoryId() {
//        Long id = 1L;
//        category.setCategoryId(id);
//        assertEquals(id, category.getCategoryId());
//    }
//
//    @Test
//    public void testSetAndGetCategoryName() {
//        String name = "Test Category";
//        category.setCategoryName(name);
//        assertEquals(name, category.getCategoryName());
//    }
//
//    @Test
//    public void testToString() {
//        String name = "Test Category";
//        category.setCategoryName(name);
//        assertEquals("Category{" + "categoryName='" + name + '\'' + '}', category.toString());
//    }
//
//    @Test
//    public void testUniqueCategoryName() {
//        Category category1 = new Category();
//        category1.setCategoryName("Unique Category");
//
//        Category category2 = new Category();
//        category2.setCategoryName("Unique Category");
//
//        entityManager.persist(category1);
//        entityManager.flush();
//
//        assertThrows(DataIntegrityViolationException.class, () -> {
//            entityManager.persist(category2);
//            entityManager.flush();
//        });
//    }
//}
