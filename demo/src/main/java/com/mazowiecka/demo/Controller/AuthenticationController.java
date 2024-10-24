//package com.mazowiecka.demo.Controller;
//
//import com.mazowiecka.demo.Service.AuthenticationService;
//import com.mazowiecka.demo.Service.UserService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/api/v1/auth")
//public class AuthenticationController {
//
//    private final UserService userService;
//    private final AuthenticationService authenticationService;
//
//
//    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
//        this.userService = userService;
//        this.authenticationService = authenticationService;
//
//    }
//
//    // Endpoint do rejestracji użytkownika
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(authenticationService.register(request));
//    }
//
//    // Endpoint do logowania
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//    }
//
//}
