package dev.srinathg.spring_security_example.service;

import dev.srinathg.spring_security_example.model.CustomUserDetails;
import dev.srinathg.spring_security_example.model.UserAuthDetails;
import dev.srinathg.spring_security_example.repo.UserAuthDetailsRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserAuthDetailsRepo userAuthDetailsRepo;

    public CustomUserDetailsService(UserAuthDetailsRepo userAuthDetailsRepo) {
        this.userAuthDetailsRepo = userAuthDetailsRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAuthDetails userAuthDetails = userAuthDetailsRepo.findByUsername(username);
        if(userAuthDetails == null) {
            System.out.println("User not found with username "+username);
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(userAuthDetails);
    }

    private List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
