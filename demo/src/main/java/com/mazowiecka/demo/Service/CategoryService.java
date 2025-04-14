package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(Long categoryId);
    public boolean categoryExists(String categoryName);
    public Category addCategory(Category category);
    public Category getCategoryByName(String categoryName);
    public Category updateCategory(Category updatedCategory, Long categoryId);

    @Transactional
    public void deleteCategory(Long categoryId);

}
