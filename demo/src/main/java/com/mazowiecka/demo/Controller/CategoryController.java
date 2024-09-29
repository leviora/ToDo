package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Category;
import com.mazowiecka.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/kategorie")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "pages/categories";
    }

    @GetMapping("/dodajKategorie")
    public String showAddCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "pages/addCategory";
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

    @GetMapping("/kategorie/edytujKategorie/{categoryId}")
    public String showEditCategoryForm(@PathVariable Long categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "pages/editCategory";
    }

    @PostMapping("/edytujKategorie/{categoryId}")
    public String processEditCategoryForm(@PathVariable Long categoryId, @ModelAttribute Category editedCategory) {
        Category categoryToUpdate = categoryService.getCategoryById(categoryId);
        categoryToUpdate.setCategoryName(editedCategory.getCategoryName());
        categoryService.updateCategory(categoryToUpdate, categoryId);
        return "redirect:/kategorie";
    }

    @GetMapping("/kategorie/usunKategorie/{categoryId}")
    public String showDeleteCategoryForm(@PathVariable Long categoryId, Model model) {
        System.out.println("GET request for deleting category with ID: " + categoryId);
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "pages/deleteCategory"; // tutaj wstaw ścieżkę do widoku HTML dla potwierdzenia usunięcia kategorii
    }

    @PostMapping("/kategorie/usunKategorie/{categoryId}")
    public String processDeleteCategoryForm(@PathVariable Long categoryId) {
        System.out.println("POST request for deleting category with ID: " + categoryId);
        categoryService.deleteCategory(categoryId);
        return "redirect:/kategorie"; // przekierowanie do listy kategorii po usunięciu
    }

}





