package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String home(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);

        List<Task> uncompletedTasks = taskService.getAllIncompleteTasks();
        model.addAttribute("uncompletedTasks", uncompletedTasks);

        List<Task> todayTasks = taskService.getTodayTasks();
        model.addAttribute("todayTasks", todayTasks);

        model.addAttribute("pageTitle", "Home Page");
        model.addAttribute("contentTemplate", "content/index :: content");

        model.addAttribute("pageTitle", "Main Page");
        return "layout/main";
    }

}
