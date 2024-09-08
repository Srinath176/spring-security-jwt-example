package dev.srinathg.spring_security_example.service;

import dev.srinathg.spring_security_example.model.UserAuthDetails;
import dev.srinathg.spring_security_example.repo.UserAuthDetailsRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthService {


   private UserAuthDetailsRepo userAuthDetailsRepo;

    public UserAuthService(UserAuthDetailsRepo userAuthDetailsRepo) {
        this.userAuthDetailsRepo = userAuthDetailsRepo;
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void create(UserAuthDetails user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userAuthDetailsRepo.save(user);
    }

    public UserAuthDetails findByUsername(String username){
       return userAuthDetailsRepo.findByUsername(username);
    }

    public void updatePassword(int id,String newPassword) {
        UserAuthDetails user = userAuthDetailsRepo.findById(id).orElseThrow();
        user.setPassword(encoder.encode(newPassword));
        userAuthDetailsRepo.save(user);
    }

    public void deleteUserbyId(int id) {
        userAuthDetailsRepo.deleteById(id);
    }

    public List<UserAuthDetails> getAllUsers(){
        return userAuthDetailsRepo.findAll();
    }
}
