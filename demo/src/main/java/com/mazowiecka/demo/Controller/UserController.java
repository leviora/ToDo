//package com.mazowiecka.demo.Controller;
//
//import com.mazowiecka.demo.Entity.Role;
//import com.mazowiecka.demo.Entity.User;
//import com.mazowiecka.demo.Service.UserService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@Controller
//public class UserController {
//
//    private final UserService userService;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    public UserController(UserService userService,
//                          BCryptPasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @GetMapping("/tworzenieUzytkownika")
//    public String createUserForm(Model model) {
//        model.addAttribute("user", new User());
//        return "pages/createUser";
//    }
//
//    @PostMapping("/tworzenieUzytkownika")
//    public String saveUser(@ModelAttribute User user, Model model) {
//        try {
//            user.setRole(Role.USER);
//            userService.saveUser(user);
//
//            return "redirect:/logowanie";
//        } catch (IllegalArgumentException e) {
//            if (e.getMessage().contains("Nazwa użytkownika jest już zajęta")) {
//                model.addAttribute("usernameError", "Nazwa użytkownika jest już zajęta.");
//            } else if (e.getMessage().contains("Hasło musi zawierać minimum 8 znaków, w tym cyfrę i znak specjalny")) {
//                model.addAttribute("passwordError", "Hasło musi zawierać minimum 8 znaków, w tym cyfrę i znak specjalny");
//            }
//            return "pages/createUser";
//        }
//    }
//
//    @GetMapping("/logowanie")
//    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
//        if (error != null) {
//            model.addAttribute("error", "Nieprawidłowa nazwa użytkownika lub hasło.");
//        }
//        return "pages/loginPage";
//    }
//
//    @PostMapping("/logowanie")
//    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
//
//        Optional<User> optionalUser = userService.getUserByUsername(username);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                session.setAttribute("loggedUserId", user.getId());
//                session.setAttribute("loggedUser", username);
//                System.out.println("Zalogowany użytkownik: " + session.getAttribute("loggedUser"));
//                System.out.println("Zalogowany użytkownik o ID: " + user.getId());
//                return "redirect:/";
//            }
//        }
//        model.addAttribute("error", "Nieprawidłowa nazwa użytkownika lub hasło.");
//        return "pages/loginPage";
//    }
//
//    @GetMapping("/wylogowanie")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        System.out.println("Użytkownik został wylogowany.");
//        return "redirect:/logowanie";
//    }
//
//
////    @PostMapping("/assignRole")
////    public String assignRole(@RequestParam String username, @RequestParam Role role, Model model) {
////        try {
////            userService.assignRoleToUser(username, role);
////            model.addAttribute("message", "Rola została przypisana pomyślnie.");
////        } catch (IllegalArgumentException e) {
////            model.addAttribute("error", e.getMessage());
////        }
////        return "pages/adminPanel";
////    }
//
//
//}
//
//
//
//
//
//
//
//
