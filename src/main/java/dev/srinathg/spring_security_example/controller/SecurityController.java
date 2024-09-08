package dev.srinathg.spring_security_example.controller;

import dev.srinathg.spring_security_example.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SecurityController {


    private List<Student> students = new ArrayList<>(List.of(new Student("srinath","cse"),new Student("Devops","Docker"))
    );
    @GetMapping("/")
    public String hello(){
        return "Hello spring security 6 with spring 3.3.2";
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        students.add(student);
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }
}
