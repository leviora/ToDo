//package com.mazowiecka.demo.Service;
//
//import com.mazowiecka.demo.Entity.Task;
//import com.mazowiecka.demo.Repository.StatsRepository;
//import com.mazowiecka.demo.Repository.TaskRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class StatsService {
//    private final TaskRepository taskRepository;
//    private final StatsRepository statsRepository;
//
//    public StatsService(TaskRepository taskRepository,
//                        StatsRepository statsRepository) {
//        this.taskRepository = taskRepository;
//        this.statsRepository = statsRepository;
//
//    }
//
//    public Map<String, Long> getCompletedTasksStats() {
//        long completed = statsRepository.countByCompleted(true);
//        long notCompleted = statsRepository.countByCompleted(false);
//        return Map.of("completed", completed, "notCompleted", notCompleted);
//    }
//
//    public Map<LocalDate, Long> getTasksCompletedPerDay() {
//        return taskRepository.findAll().stream()
//                .filter(Task::isCompleted)
//                .collect(Collectors.groupingBy(Task::getDue_Date, Collectors.counting()));
//    }
//
//}
