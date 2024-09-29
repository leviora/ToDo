package com.mazowiecka.demo.Service;

import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id, Task task) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Task updatedTask, Long taskId) {
        return taskRepository.save(updatedTask);
    }

    public long countAllTask() {
        return taskRepository.count();
    }

    public List<Task> getLatestTasks() {
        List<Task> Latesttasks = taskRepository.findAll();

        Latesttasks = Latesttasks.stream()
                .filter(task -> task.getDue_Date() != null)
                .sorted((task1, task2) -> task1.getDue_Date().compareTo(task2.getDue_Date()))
                .collect(Collectors.toList());
        return Latesttasks;
    }

    public List<Task> getTodayTasks() {
        List<Task> tasks = taskRepository.findAll();
        LocalDate today = LocalDate.now();

        List<Task> todayTasks = tasks.stream()
                .filter(task -> task.getDue_Date() != null && isSameDay(today, task.getDue_Date()))
                .collect(Collectors.toList());
        return todayTasks;
    }

    private boolean isSameDay(LocalDate localDate1, LocalDate localDate2) {
        return localDate1.isEqual(localDate2);
    }

    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = taskRepository.findAll();
        completedTasks = completedTasks.stream().filter(task -> task.isCompleted() == true).collect(Collectors.toList());
        return completedTasks;
    }

    public List<Task> findTasksByPriority(String priority) {
        return taskRepository.findTasksByPriority(priority);
    }

    public void deleteCompletedTasks() {
        List<Task> completedTasks = taskRepository.findByCompleted(true);
        taskRepository.deleteAll(completedTasks);
    }

    public List<Task> getAllUncompleteTasks() {
        List<Task> uncompletedTasks = taskRepository.findByCompletedFalse();
        return uncompletedTasks;
    }

}
