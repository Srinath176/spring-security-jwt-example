package dev.srinathg.spring_security_example.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final UserAuthDetails userAuthDetails;

    public CustomUserDetails(UserAuthDetails userAuthDetails) {
        this.userAuthDetails = userAuthDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return userAuthDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userAuthDetails.getUsername();
    }
}
