package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Service.TaskService;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private final TaskService taskService;
    private final UserService userService;

    public HomeController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            List<Task> userTasks = taskService.getTasksByUsername(username);
            model.addAttribute("tasks", userTasks);
        }
        return "index";
    }
}

