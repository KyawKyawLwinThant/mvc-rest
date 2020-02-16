package com.example.demorest.controller;

import com.example.demorest.domain.Student;
import com.example.demorest.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(value="/home",produces = MediaType.TEXT_PLAIN_VALUE)
    public String welcome(){
        //return new ResponseEntity("welcome",HttpStatus.OK);
        return "Welcome Spring MVC REST";
    }
    @GetMapping("/creation")
    public String create(){
        Arrays.asList(new Student("Thaw Thaw",23,"Latha"),
                new Student("Maw Maw",22,"Mdy"),
                new Student("Thuza",25,"Latha"))
                .forEach(studentRepository::save);
        return "successfully created.";
    }
    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> showAllStudents(){
        return this.studentRepository.findAll();
    }
    @GetMapping(value = "/all/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findStudentById(@PathVariable int id){
        return this.studentRepository.getOne(id);
    }
}
