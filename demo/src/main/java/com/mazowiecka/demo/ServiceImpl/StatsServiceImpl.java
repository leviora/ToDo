package com.mazowiecka.demo.ServiceImpl;

import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Repository.StatsRepository;
import com.mazowiecka.demo.Repository.TaskRepository;
import com.mazowiecka.demo.Service.StatsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService {
    private final TaskRepository taskRepository;
    private final StatsRepository statsRepository;

    public StatsServiceImpl(TaskRepository taskRepository,
                            StatsRepository statsRepository) {
        this.taskRepository = taskRepository;
        this.statsRepository = statsRepository;

    }

    @Override
    public Map<String, Long> getCompletedTasksStats() {
        long completed = statsRepository.countByCompleted(true);
        long notCompleted = statsRepository.countByCompleted(false);
        return Map.of("completed", completed, "notCompleted", notCompleted);
    }

    @Override
    public Map<LocalDate, Long> getTasksCompletedPerDay() {
        return taskRepository.findAll().stream()
                .filter(Task::isCompleted)
                .collect(Collectors.groupingBy(Task::getDue_Date, Collectors.counting()));
    }

}
