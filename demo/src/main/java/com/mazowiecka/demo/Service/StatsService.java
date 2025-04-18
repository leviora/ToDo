package com.mazowiecka.demo.Service;

import java.time.LocalDate;
import java.util.Map;

public interface StatsService {

    public Map<String, Long> getCompletedTasksStats();

    public Map<LocalDate, Long> getTasksCompletedPerDay();
}
