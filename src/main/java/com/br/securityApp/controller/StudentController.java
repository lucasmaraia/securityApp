package com.br.securityApp.controller;

import com.br.securityApp.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

   private List<Student> students = new ArrayList<>(
            List.of(new Student(1,3030,"lucas"),
                    new Student(2,3031,"mateus")

            ));

    @GetMapping
    public List<Student> getStudent(){
        return students;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }
}
