package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Task;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public interface StatsService {

    public Map<String, Long> getCompletedTasksStats();

    public Map<LocalDate, Long> getTasksCompletedPerDay();
}
