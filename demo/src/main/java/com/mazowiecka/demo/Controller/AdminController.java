package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.DTO.UserDTO;
import com.mazowiecka.demo.Entity.Role;
import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Mapper.UserMapper;
import com.mazowiecka.demo.Service.RoleService;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService,
                           RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
    public String showUserDetails(@PathVariable Long id, Model model) {
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
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "pages/admin/admin-user-edit";
    }

    @PostMapping("/uzytkownicy/{id}/edytuj")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User updatedUser) {
        User existingUser = userService.getUserById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        userService.updateUser(id, existingUser);
        return "redirect:/admin/uzytkownicy";
    }

//    @GetMapping("/uzytkownicy/{id}/edytuj")
//    public String editUserForm(@PathVariable Long id, Model model) {
//        User user = userService.getUserById(id);
//        UserDTO userDTO = UserMapper.INSTANCE.toDTO(user);
//
//        // 🔽 domyślnie ustawiamy pierwszą rolę jako wybraną (jeśli istnieje)
//        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
//            userDTO.setRole(user.getRoles().iterator().next().getName().name());
//        }
//
//        model.addAttribute("user", userDTO);
//        return "pages/admin/admin-user-edit";
//    }
//
//    @PostMapping("/uzytkownicy/{id}/edytuj")
//    public String updateUser(@PathVariable Long id, @ModelAttribute("user") UserDTO userDTO) {
//        User user = userService.getUserById(id);
//        user.setUsername(userDTO.getUsername());
//        user.setEmail(userDTO.getEmail());
//
//        // Pobieramy rolę z bazy danych na podstawie Stringa
//        if (userDTO.getRole() != null) {
//            Role.RoleName roleName = Role.RoleName.valueOf(userDTO.getRole());
//            Role role = roleService.getRoleByName(roleName);
//            user.setRoles(Set.of(role));
//        }
//
//        userService.updateUser(id, user);
//        return "redirect:/admin/uzytkownicy";
//    }


}
