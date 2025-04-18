package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    public List<Task> getAllTasks();

    public Optional<Task> getTaskById(Long id);

    public Task addTask(Task task);

    public void deleteTask(Long taskId);

    public Task updateTask(Task updatedTask, Long taskId);

    public List<Task> getTodayTasks();

    public boolean isSameDay(LocalDate localDate1, LocalDate localDate2);

    public List<Task> getCompletedTasks();

    public List<Task> findTasksByPriority(String priority);

    public void deleteCompletedTasks();

    public String calculateDynamicPriority(Task task);

    public List<Task> getUncompletedTodayTasks();

    List<Task> findTasksByUserId(Long userId);

    public List<Task> getTasksByUsername(String username);

}
