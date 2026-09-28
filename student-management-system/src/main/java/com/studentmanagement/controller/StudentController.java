package com.studentmanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;


    public StudentController(StudentService service) {

        this.service = service;

    }


    // CREATE

    @PostMapping
    public ResponseEntity<Student> addStudent(
            @Valid @RequestBody Student student) {

        Student savedStudent = service.addStudent(student);

        return new ResponseEntity<>(
                savedStudent,
                HttpStatus.CREATED
        );
    }


    // GET ALL

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        List<Student> students = service.getAllStudents();

        return ResponseEntity.ok(students);

    }


    // GET BY ID

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable int id) {

        Student student = service.getStudentById(id);

        return ResponseEntity.ok(student);

    }


    // UPDATE

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @Valid @RequestBody Student student) {

        Student updatedStudent =
                service.updateStudent(id, student);

        return ResponseEntity.ok(updatedStudent);

    }


    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable int id) {

        service.deleteStudent(id);

        return ResponseEntity.ok(
                "Student with id " + id + " deleted successfully"
        );

    }
}