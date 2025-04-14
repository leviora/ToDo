package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Service.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class UserDashboardController {

    private final TaskService taskService;

    public UserDashboardController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String showUserDashboard(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            List<Task> userTasks = taskService.getTasksByUsername(username);
            List<Task> todayTasks = taskService.getUncompletedTodayTasks();
            model.addAttribute("tasks", userTasks);
            model.addAttribute("todayTasks", todayTasks);
        }
        return "pages/dashboard/userDashboard";
    }
}
