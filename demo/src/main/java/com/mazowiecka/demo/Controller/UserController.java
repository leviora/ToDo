//package com.mazowiecka.demo.Controller;
//
//import com.mazowiecka.demo.Entity.User;
//import com.mazowiecka.demo.Service.UserService;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@PreAuthorize("hasRole('ADMIN')")
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
//}
//
//
//
//
//
//
//
//
