package com.mazowiecka.demo.Controller;

import com.mazowiecka.demo.Entity.User;
import com.mazowiecka.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "pages/auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "pages/auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user,
                           Model model) {
        try {
            userService.createUser(user);
            return "redirect:/pages/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "pages/auth/register";
        }
    }
}
