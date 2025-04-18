package com.mazowiecka.demo.ServiceImpl;

import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Repository.TaskRepository;
import com.mazowiecka.demo.Repository.UserRepository;
import com.mazowiecka.demo.Service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task updatedTask,
                           Long taskId) {
        return taskRepository.save(updatedTask);
    }

    @Override
    public List<Task> getTodayTasks() {
        List<Task> tasks = taskRepository.findAll();
        LocalDate today = LocalDate.now();

        List<Task> todayTasks = tasks.stream()
                .filter(task -> task.getDue_Date() != null && isSameDay(today, task.getDue_Date()))
                .collect(Collectors.toList());
        return todayTasks;
    }

    @Override
    public boolean isSameDay(LocalDate localDate1,
                             LocalDate localDate2) {
        return localDate1.isEqual(localDate2);
    }

    @Override
    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = taskRepository.findAll();
        completedTasks = completedTasks.stream().filter(task -> task.isCompleted() == true).collect(Collectors.toList());
        return completedTasks;
    }

    @Override
    public List<Task> findTasksByPriority(String priority) {
        return taskRepository.findTasksByPriority(priority);
    }

    @Override
    public void deleteCompletedTasks() {
        List<Task> completedTasks = taskRepository.findByCompleted(true);
        taskRepository.deleteAll(completedTasks);
    }

    @Override
    public String calculateDynamicPriority(Task task) {
        if (task.getDue_Date() == null) {
            return "brak terminu";
        }
        LocalDate today = LocalDate.now();
        LocalDate dueDate = task.getDue_Date();

        long daysLeft = ChronoUnit.DAYS.between(today, dueDate);
        if (daysLeft < 0) {
            return "zadania przeterminowane";
        } else if (daysLeft <= 1) {
            return "wysoki priorytet";
        } else if (daysLeft <= 3) {
            return "średni priorytet";
        } else {
            return "niski priorytet";
        }
    }

    @Override
    public List<Task> getUncompletedTodayTasks() {
        List<Task> todayTasks = getTodayTasks();
        return todayTasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> findTasksByUserId(Long userId) {
        return taskRepository.findAllByUser_Id(userId);
    }

    @Override
    public List<Task> getTasksByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return taskRepository.findByUser(user);
    }

}
