package com.mazowiecka.demo.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public StatsController(StatsService statsService,
                           UserService userService) {
        this.statsService = statsService;
        this.userService = userService;
    }

    @GetMapping
    public String showStatsView() {
        return "pages/stats/mainView";
    }

    @GetMapping("/zakonczone-zadania")
    public String getCompletedTasksStats(Model model) {
        Map<String, Long> completedTasksStats = statsService.getCompletedTasksStats();
        model.addAttribute("completedTasksStats", completedTasksStats);
        return "pages/stats/completedTasks";
    }

    @GetMapping("/postęp")
    public String getTasksProgressStats(Model model,
                                        HttpSession session) throws JsonProcessingException {
        Map<LocalDate, Long> progressStats = statsService.getTasksCompletedPerDay();

        ObjectMapper objectMapper = new ObjectMapper();
        String progressStatsJson = objectMapper.writeValueAsString(progressStats);

        model.addAttribute("progressStatsJson", progressStatsJson);

        return "pages/stats/progress";
    }

}
