package dev.srinathg.spring_security_example.controller;

import dev.srinathg.spring_security_example.model.Users;
import dev.srinathg.spring_security_example.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    public List<Users> getUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/users/{firstName}")
    public Users getUserByName(@PathVariable String firstName){
        return userRepository.findByFirstName(firstName);
    }


    @PostMapping("users")
    public ResponseEntity<Users> createUser(@RequestBody Users users){
        userRepository.save(users);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable int id){
        userRepository.deleteById(id);
    }
}
