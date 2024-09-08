package dev.srinathg.spring_security_example.repo;

import dev.srinathg.spring_security_example.model.UserAuthDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthDetailsRepo extends JpaRepository<UserAuthDetails,Integer> {

    UserAuthDetails findByUsername(String username);
}
