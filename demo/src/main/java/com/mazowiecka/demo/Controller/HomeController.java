package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Task;
//import com.mazowiecka.demo.Service.TaskService;
import com.mazowiecka.demo.Service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
//    @Autowired
//    private TaskService taskService;
//
//    @GetMapping("/")
//    public String home(Model model, HttpSession session) {
//        List<Task> tasks = taskService.getAllTasks();
//        model.addAttribute("tasks", tasks);
//
//        List<Task> todayTasks = taskService.getTodayTasks();
//        model.addAttribute("todayTasks", todayTasks);
//
//        model.addAttribute("pageTitle", "Home Page");
//        model.addAttribute("contentTemplate", "content/index :: content");
//
//        String loggedUser = (String) session.getAttribute("loggedUser");
//        System.out.println("Zalogowany użytkownik w HomeController: " + loggedUser);
//        if (loggedUser != null) {
//            model.addAttribute("loggedUser", loggedUser);
//        }
//        model.addAttribute("isLoggedIn", loggedUser != null);
//
//        return "layout/main";
//    }

    private  final TaskService taskService;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";
    }

}
