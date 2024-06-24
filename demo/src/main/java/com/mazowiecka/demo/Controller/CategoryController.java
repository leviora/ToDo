package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Category;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Exception.CategoryNotFoundException;
import com.mazowiecka.demo.Exception.TaskNotFoundException;
import com.mazowiecka.demo.Repository.TaskRepository;
import com.mazowiecka.demo.Service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class CategoryController {
    private final CategoryService categoryService;
//    private final TaskRepository taskRepository;
//
//    public CategoryController(CategoryService categoryService, TaskRepository taskRepository) {
//        this.categoryService = categoryService;
//        this.taskRepository = taskRepository;
//    }
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/kategorie")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "fragments/categories";
    }
    @GetMapping("/dodajKategorie")
    public String showAddCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "fragments/addCategoryForm";
    }
    @PostMapping("/dodajKategorie")
    public String addCategory(@ModelAttribute("category") Category category) {
        if (!categoryService.categoryExists(category.getCategoryName())) {
            categoryService.addCategory(category);
        } else {
            return "fragments/categoryExist";
        }
        return "redirect:/kategorie";
    }
    @GetMapping("/edytujKategorie")
    public String showCategoryToEdit(Model model) {
        List<Category> categories =  categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "fragments/showCategoryToEdit";
    }
    @GetMapping("/edytujKategorie/{categoryId}")
    public String showEditFormCategory(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);
        model.addAttribute("categoryId", categoryId);
        return "fragments/editCategoryForm";
    }
    @PostMapping("/edytujKategorie/{categoryId}")
    public String editCategory(@PathVariable("categoryId") Long categoryId, @ModelAttribute Category updatedCategory) {
        Category existingCategory = categoryService.getCategoryById(categoryId);
        if(existingCategory != null) {
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
        }
        categoryService.updateCategory(existingCategory,categoryId);
        return "redirect:/";
    }
    @GetMapping("/usunKategorie")
    public String showDeleteCategory(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "fragments/showCategoryToDelete";
    }
    @GetMapping("/usunKategorie/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Long categoryId, @ModelAttribute Category deleteCategory) {
        Category existingCategry = categoryService.getCategoryById(categoryId);
        categoryService.deleteCategory(deleteCategory,categoryId);
        return "redirect:/";
    }

}
