//package com.mazowiecka.demo.Service;
//
//import com.mazowiecka.demo.Config.JwtService;
//import com.mazowiecka.demo.Controller.AuthenticationRequest;
//import com.mazowiecka.demo.Controller.AuthenticationResponse;
//import com.mazowiecka.demo.Controller.RegisterRequest;
//import com.mazowiecka.demo.Entity.Role;
//import com.mazowiecka.demo.Entity.User;
//import com.mazowiecka.demo.Repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//
//    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .username(request.getUsername())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
//        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//// kod z tutorialu
////    public AuthenticationResponse authenticate(AuthenticationRequest request) {
////
////        authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        request.getUsername(),
////                        request.getPassword()
////                )
////        );
////        var user = repository.findByUsername(request.getUsername())
////                .orElseThrow();
////        var jwtToken = jwtService.generateToken(user);
////        return AuthenticationResponse.builder()
////                .token(jwtToken)
////                .build();
////    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        System.out.println("Authenticating user: " + request.getUsername());
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//        var user = repository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        System.out.println("Authenticated user: " + user.getUsername());
//        var jwtToken = jwtService.generateToken(user);
//        System.out.println("Token JWT: " + jwtToken);
//
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//
//}
