//package com.mazowiecka.demo.Service;
//
//import com.mazowiecka.demo.Entity.User;
//import com.mazowiecka.demo.Model.CustomUserDetails;
//import com.mazowiecka.demo.Repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
//    private final UserRepository userRepository;
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        // Dodaj logowanie, aby zobaczyć, co zwraca metoda
//        logger.info("Próba logowania użytkownika: " + username);
//        if (user == null) {
//            logger.error("Nie znaleziono użytkownika: " + username);
//            throw new UsernameNotFoundException("Nie znaleziono użytkownika o podanej nazwie.");
//        }
//
//        logger.info("Znaleziono użytkownika: " + user.getUsername());
//        return new CustomUserDetails(user.getUsername(), user.getPassword());  // Zwracamy naszą implementację UserDetails
//    }
//}
