package com.mazowiecka.demo.ServiceImpl;

import com.mazowiecka.demo.Entity.Category;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Repository.CategoryRepository;
import com.mazowiecka.demo.Repository.TaskRepository;
import com.mazowiecka.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, TaskRepository taskRepository) {
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
    }

    @Value("${default.category.id}")
    private Long defaultCategoryId;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
    }

    @Override
    public boolean categoryExists(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);
        return categoryOptional.orElse(null);
    }

    @Override
    public Category updateCategory(Category updatedCategory, Long categoryId) {
        return categoryRepository.save(updatedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {

        Category defaultCategory = categoryRepository.findById(defaultCategoryId)
                .orElseThrow(() -> new RuntimeException("Default category not found with ID: " + defaultCategoryId));

        Category categoryToDelete = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

        List<Task> tasks = taskRepository.findByCategory_CategoryId(categoryId);

        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                task.setCategory(defaultCategory);
            }
            taskRepository.saveAll(tasks);
        }
        categoryRepository.deleteById(categoryId);
    }

}





