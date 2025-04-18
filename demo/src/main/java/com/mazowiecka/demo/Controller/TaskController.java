package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Category;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Exception.TaskNotFoundException;
import com.mazowiecka.demo.Repository.CategoryRepository;
import com.mazowiecka.demo.Repository.TaskRepository;
import com.mazowiecka.demo.Repository.UserRepository;
import com.mazowiecka.demo.Service.CategoryService;
import com.mazowiecka.demo.Service.TaskService;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@Controller
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final UserRepository userRepository;

    private final UserService userService;

    public TaskController(TaskRepository taskRepository,
                          TaskService taskService,
                          CategoryRepository categoryRepository,
                          CategoryService categoryService,
                          UserRepository userRepository,
                          UserService userService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/dodajZadanie")
    public String showAddTaskPage(Model model) {
        Task task = new Task();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("task", task);
        model.addAttribute("categories", categories);
        return "pages/user/addTask";
    }

    @PostMapping("/dodajZadanie")
    public String addTask(@ModelAttribute Task task,
                          @RequestParam(name = "categoryId", required = false) Long categoryId,
                          @RequestParam(name = "newCategory", required = false) String newCategoryName) {
        Category category;
        if (newCategoryName != null && !newCategoryName.isEmpty()) {
            category = new Category();
            category.setCategoryName(newCategoryName);
            categoryService.addCategory(category);
        } else {
            category = categoryService.getCategoryById(categoryId);
        }

        task.setCategory(category);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        task.setUser(user);

        taskService.addTask(task);
        return "redirect:/dashboard";
    }

    @GetMapping("/edytujZadanie/{taskId}")
    public String showEditForm(@PathVariable("taskId") Long taskId,
                               Model model) {
        Task task = taskService.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
        model.addAttribute("task", task);
        return "pages/user/EditForm";
    }

    @PostMapping("/edytujZadanie/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId,
                           @ModelAttribute Task updatedTask,
                           Model model) {

        Task existingTask = taskService.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
        Category updatedCategory = updatedTask.getCategory();

        if (updatedCategory != null) {

            existingTask.getCategory().setCategoryId(updatedCategory.getCategoryId());
        }
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setDue_Date(updatedTask.getDue_Date());

        taskService.updateTask(existingTask, taskId);
        return "redirect:/zarzadzaj-zadaniami";

    }

    @PostMapping("/usunZadanie/{taskId}")
    public String usunZadanie(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/zarzadzaj-zadaniami";
    }

    @PostMapping("/changeStatus/{taskId}")
    public String changeTaskStatus(@PathVariable Long taskId,
                                   Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        Task task = taskService.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
        task.setCompleted(true);
        taskService.updateTask(task, taskId);
        return "redirect:/dashboard";
    }

    @GetMapping("/zakonczoneZadania")
    public String showCompletedTasks(Model model) {
        List<Task> completedTasks = taskService.getCompletedTasks();
        model.addAttribute("completedTasks", completedTasks);
        return "pages/completedTasks";
    }

//    @GetMapping("/sortuj")
//    public String sortTasks(@RequestParam("sortOption") String sortOption,
//                            Model model) {
//        List<Task> sortedTasks = getSortedTasks(sortOption);
//        model.addAttribute("sortedTasks", sortedTasks);
//        model.addAttribute("sortOption", sortOption);
//        return "/pages/sort";
//    }
//    private List<Task> getSortedTasks(String sortOption) {
//        switch (sortOption) {
//            case "date":
//                return taskRepository.findAllByOrderByDueDateAsc();
//            case "importantUrgent":
//                return taskService.findTasksByPriority("urgent-important");
//            case "importantNotUrgent":
//                return taskService.findTasksByPriority("not-urgent-important");
//            case "notImportantUrgent":
//                return taskService.findTasksByPriority("urgent-not-important");
//            case "notImportantNotUrgent":
//                return taskService.findTasksByPriority("not-urgent-not-important");
//            default:
//                return taskRepository.findAll();
//        }
//    }

    @PostMapping("/usunZakonczoneZadania")
    public String deleteCompletedTasks() {
        taskService.deleteCompletedTasks();
        return "pages/user/manage-tasks";
    }

    @GetMapping("/zarzadzaj-zadaniami")
    public String showManageTasks(Model model) {
        User user = userService.getCurrentUser();
        List<Task> userTasks = taskService.findTasksByUserId(user.getId());

        model.addAttribute("tasks", userTasks);
        return "pages/user/manage-tasks";
    }


}