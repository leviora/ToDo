package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.DTO.UserDTO;
import com.mazowiecka.demo.Entity.Task;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Mapper.UserMapper;
import com.mazowiecka.demo.Service.RoleService;
import com.mazowiecka.demo.Service.TaskService;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final TaskService taskService;

    public AdminController(UserService userService,
                           TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping
    public String showAdminDashboard() {
        return "pages/dashboard/admin-dashboard";
    }

    @GetMapping("/uzytkownicy")
    public String listUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers()
                .stream()
                .map(UserMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "pages/admin/admin-users";
    }

    @GetMapping("/uzytkownicy/{id}")
    public String showUserDetails(@PathVariable Long id,
                                  Model model) {
        User user = userService.getUserById(id);
        UserDTO userDTO = UserMapper.INSTANCE.toDTO(user);
        model.addAttribute("user", userDTO);
        return "pages/admin/admin-user-details";
    }

    @PostMapping("/uzytkownicy/{id}/usuń")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/uzytkownicy";
    }

    @GetMapping("/uzytkownicy/{id}/edytuj")
    public String editUserForm(@PathVariable Long id,
                               Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "pages/admin/admin-user-edit";
    }

    @PostMapping("/uzytkownicy/{id}/edytuj")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user") User updatedUser) {
        User existingUser = userService.getUserById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        userService.updateUser(id, existingUser);
        return "redirect:/admin/uzytkownicy";
    }

    @GetMapping("/statystyki")
    public String showStatistics(Model model) {
        List<User> users = userService.getAllUsers();
        List<Task> allTasks = taskService.getAllTasks();

        // Średnia liczba zadań na użytkownika
        double avgTasks = users.isEmpty() ? 0 : (double) allTasks.size() / users.size();
        model.addAttribute("avgTasks", avgTasks);

        // Postęp – zakończone zadania w czasie
        Map<LocalDate, Long> completedByDate = allTasks.stream()
                .filter(Task::isCompleted)
                .filter(task -> task.getDue_Date() != null)
                .collect(Collectors.groupingBy(Task::getDue_Date, TreeMap::new, Collectors.counting()));

        List<String> progressDates = completedByDate.keySet().stream()
                .map(LocalDate::toString).toList();
        List<Long> progressCounts = new ArrayList<>(completedByDate.values());

        model.addAttribute("progressDates", progressDates);
        model.addAttribute("progressCounts", progressCounts);

        // Nowi użytkownicy w czasie
        Map<LocalDate, Long> newUsers = users.stream()
                .filter(user -> user.getCreatedDate() != null)
                .collect(Collectors.groupingBy(User::getCreatedDate, TreeMap::new, Collectors.counting()));

        List<String> userCreationDates = newUsers.keySet().stream()
                .map(LocalDate::toString).toList();
        List<Long> userCreationCounts = new ArrayList<>(newUsers.values());

        model.addAttribute("userCreationDates", userCreationDates);
        model.addAttribute("userCreationCounts", userCreationCounts);

        return "pages/admin/statistics";
    }


}
