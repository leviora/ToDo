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
    public String addAttributes(Model model) {

        List<Task> uncompletedTodayTasks = taskService.getUncompletedTodayTasks();
        model.addAttribute("uncompletedTodayTasks", uncompletedTodayTasks);
        Model uncompletedTodayTasksCount = model.addAttribute("uncompletedTodayTasksCount", uncompletedTodayTasks.size());
        return "todayTasks";
    }
}
