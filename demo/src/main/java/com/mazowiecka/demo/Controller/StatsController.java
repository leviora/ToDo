package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Service.StatsService;
import com.mazowiecka.demo.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/statystyki")
public class StatsController {
    private final StatsService statsService;
    private final UserService userService;

    public StatsController(StatsService statsService, UserService userService) {
        this.statsService = statsService;
        this.userService = userService;
    }

    @GetMapping
    public String showStatsView() {
        return "stats/mainView";
    }

    @GetMapping("/zakonczone-zadania")
    public String getCompletedTasksStats(Model model, HttpSession session) {
        Map<String, Long> completedTasksStats = statsService.getCompletedTasksStats();
        model.addAttribute("completedTasksStats", completedTasksStats);

        if(userService.isLoggedIn(session)) {
            String loggedUser = (String) session.getAttribute("loggedUser");
            model.addAttribute("loggedUser", loggedUser);
        }

        return "stats/completed-tasks";
    }

    @GetMapping("/postęp")
    public String getTasksProgressStats(Model model, HttpSession session) {
        Map<LocalDate, Long> progressStats = statsService.getTasksCompletedPerDay();
        model.addAttribute("progressStats", progressStats);

        if(userService.isLoggedIn(session)) {
            String loggedUser = (String) session.getAttribute("loggedUser");
            model.addAttribute("loggedUser", loggedUser);
        }

        return "stats/progress";
    }


}
