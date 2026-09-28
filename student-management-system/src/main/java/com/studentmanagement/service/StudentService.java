package com.example.studentmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;


    public StudentService(StudentRepository repository) {

        this.repository = repository;

    }


    // CREATE

    public Student addStudent(Student student) {

        return repository.save(student);

    }


    // GET ALL

    public List<Student> getAllStudents() {

        return repository.findAll();

    }


    // GET BY ID

    public Student getStudentById(int id) {

        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

    }


    // UPDATE

    public Student updateStudent(int id, Student newStudent) {

        Student existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));


        existingStudent.setName(newStudent.getName());

        existingStudent.setEmail(newStudent.getEmail());

        existingStudent.setCourse(newStudent.getCourse());

        existingStudent.setAge(newStudent.getAge());


        return repository.save(existingStudent);

    }


    // DELETE

    public void deleteStudent(int id) {

        Student existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));


        repository.delete(existingStudent);

    }
}