package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Category;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Repository.CategoryRepository;
import com.mazowiecka.demo.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;
    public CategoryService(CategoryRepository categoryRepository, TaskRepository taskRepository) {
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
    }
    @Value("${default.category.id}")
    private Long defaultCategoryId;
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
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
    public Category updateCategory(Category updatedCategory, Long categoryId) {
        return categoryRepository.save(updatedCategory);
    }
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    @Transactional
    public void deleteCategory(Long categoryId) {
        logger.info("Attempting to delete category with ID: " + categoryId);

        Category defaultCategory = categoryRepository.findById(defaultCategoryId)
                .orElseThrow(() -> new RuntimeException("Default category not found with ID: " + defaultCategoryId));

        Category categoryToDelete = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

        List<Task> tasks = taskRepository.findByCategory_CategoryId(categoryId);
        logger.info("Tasks to reassign: " + tasks.size());

        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                task.setCategory(defaultCategory);
                logger.info("Reassigning task ID: " + task.getTaskId() + " to default category ID: " + defaultCategory.getCategoryId());
            }
            taskRepository.saveAll(tasks);
            logger.info("Tasks reassigned successfully.");
        }

        categoryRepository.deleteById(categoryId);
        logger.info("Successfully deleted category with ID: " + categoryId);
    }

}





