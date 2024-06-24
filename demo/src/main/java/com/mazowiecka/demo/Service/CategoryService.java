package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Category;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Exception.CategoryNotFoundException;
import com.mazowiecka.demo.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

        public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Kategoria o id " + categoryId + " nie istnieje."));
    }
    public boolean categoryExists(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryByName(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);
        return categoryOptional.orElse(null);
    }
    public Category updateCategory(Category updatedCategory, Long categoryId ) {
        return categoryRepository.save(updatedCategory);
    }
    public void deleteCategory(Category deletedCategory, Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
//    public Set<String> getAllCategories() {
//        Set<String> categories = new HashSet<>();
//        List<Category> categoryList = categoryRepository.findAll();
//        for (Category category : categoryList) {
//            if (category.getCategoryName() != null && !category.getCategoryName().isEmpty()) {
//                categories.add(category.getCategoryName());
//            }
//        }
//        return categories;
//    }
