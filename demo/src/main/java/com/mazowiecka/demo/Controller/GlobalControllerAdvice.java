package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final TaskService taskService;

    @Autowired
    public GlobalControllerAdvice(TaskService taskService) {
        this.taskService = taskService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("uncompletedTasks", taskService.getAllUncompleteTasks());
    }
}
