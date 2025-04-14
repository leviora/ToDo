package com.mazowiecka.demo.DTO;

import com.mazowiecka.demo.Entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    Long id;
    String username;
    String email;
    Set<Role> roles;
    String role;
}
