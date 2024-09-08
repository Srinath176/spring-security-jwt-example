package dev.srinathg.spring_security_example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
public class UserAuthDetails {

    @Id
    private int id;
    @Column(unique = true)
    private String username;
    private String password;


    public UserAuthDetails() {
    }

    public UserAuthDetails(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
