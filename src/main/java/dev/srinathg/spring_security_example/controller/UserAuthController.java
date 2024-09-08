package dev.srinathg.spring_security_example.controller;

import dev.srinathg.spring_security_example.model.UserAuthDetails;
import dev.srinathg.spring_security_example.security.JwtService;
import dev.srinathg.spring_security_example.service.UserAuthService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAuthController {

    private UserAuthService userAuthService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("/authenticate")
    public String verify(@RequestBody UserAuthDetails user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }

    @GetMapping("/auth-users")
    public List<UserAuthDetails> getAllusers() {
        return userAuthService.getAllUsers();
    }

    @PostMapping("/user-details")
    public ResponseEntity<UserAuthDetails> createUserDetails(@RequestBody UserAuthDetails userAuthDetails) {
        userAuthService.create(userAuthDetails);
        return new ResponseEntity<>(userAuthDetails, HttpStatus.CREATED);
    }

    @PutMapping("/user-details/{id}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable int id, @RequestBody String password) {
        userAuthService.updatePassword(id, password);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("user-details/{id}")
    public void deleteUserById(@PathVariable int id) {
        userAuthService.deleteUserbyId(id);
    }
}
