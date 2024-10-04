package com.mazowiecka.demo.Model;

import com.mazowiecka.demo.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false; // zmienić na true, gdy konto bedzie wygasało
    }

    @Override
    public boolean isAccountNonLocked() {
        return false; // zmienić na true, gdy konto nigdy nie będzie zablokowane
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false; // zmienić na true, gdy dane uwierzytelniające nigdy nie wygasają
    }

    @Override
    public boolean isEnabled() {
        return false; // zmienić na true, gdy konto użytkownika jest zawsze aktywne
    }
}
