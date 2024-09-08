package dev.srinathg.spring_security_example.repo;

import dev.srinathg.spring_security_example.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<Users,Integer> {

    Users findByFirstName(String firstName);

}
