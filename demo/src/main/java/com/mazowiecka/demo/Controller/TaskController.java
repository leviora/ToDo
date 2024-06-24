package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Category;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Repository.CategoryRepository;
import com.mazowiecka.demo.Repository.TaskRepository;
import com.mazowiecka.demo.Service.CategoryService;
import com.mazowiecka.demo.Service.TaskService;
import com.mazowiecka.demo.Exception.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private CategoryRepository categryRepository;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/dodajZadanie")
    public String showAddTaskPage(Model model) {
        Task task = new Task();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("task", task);
        model.addAttribute("categories", categories);
        return "addTask";
    }
    @PostMapping("/dodajZadanie")
    public String addTask(@ModelAttribute("task") Task task) {
        taskService.addTask(task);
        return "redirect:/";
    }
    @GetMapping("/edytuj")
    public String showTasktoEdit(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "showTaskListToEdit";
    }
    @GetMapping("/edytuj/{taskId}")
    public String showEditForm(@PathVariable("taskId") Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
        model.addAttribute("task", task);
        return "editForm";
    }
    @PostMapping("/edytuj/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId, @ModelAttribute Task updatedTask, Model model) {
        Task existingTask = taskService.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));

        Category updatedCategory = updatedTask.getCategory();

        if (updatedCategory != null) {

            existingTask.getCategory().setCategoryId(updatedCategory.getCategoryId());
        } else {
            // Obsługa błędu - jeśli kategoria jest nullem
        }

        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setDue_Date(updatedTask.getDue_Date());

        taskService.updateTask(existingTask, taskId);

        return "redirect:/";
    }
    @GetMapping("/usun")
    public String showTasktoDelete(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "showTaskListToDelete";
    }
    @GetMapping("/usun/{taskId}")
    public String deleteTask(@PathVariable("taskId") Long taskId, @ModelAttribute Task deletedTask, Model model) {
        taskService.deleteTask(taskId, deletedTask);
        return "redirect:/";
    }
    @PostMapping("/changeStatus/{taskId}")
    public String changeTaskStatus(@PathVariable Long taskId, Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        Task task = taskService.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
        task.setCompleted(true);
        taskService.updateTask(task, taskId);
        return "redirect:/";
    }
    @GetMapping("/zakonczoneZadania")
    public String showCompletedTasks(Model model) {
        List<Task> completedTasks = taskService.getCompletedTasks();
        model.addAttribute("completedTasks", completedTasks);
        return "fragments/completedtasks";
    }
    @GetMapping("/sortuj")
    public String sortTasks(@RequestParam("sortOption") String sortOption, Model model) {

        List<Task> sortedTasks;
        switch (sortOption) {
            case "date":
                sortedTasks = taskRepository.findAllByOrderByDueDateAsc();
                break;
            case "importantUrgent":
                sortedTasks = taskService.findTasksByPriority("urgent-important");
                break;
            case "importantNotUrgent":
                sortedTasks = taskService.findTasksByPriority("not-urgent-important");
                break;
            case "notImportantUrgent":
                sortedTasks = taskService.findTasksByPriority("urgent-not-important");
                break;
            case "notImportantNotUrgent":
                sortedTasks = taskService.findTasksByPriority("not-urgent-not-important");
                break;
            default:
                sortedTasks = taskRepository.findAll();
                break;
        }
        model.addAttribute("sortedTasks", sortedTasks);
        model.addAttribute("sortOption", sortOption);
        return "sortedList";
    }


}
