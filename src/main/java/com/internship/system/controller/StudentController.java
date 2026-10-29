package com.internship.system.controller;

import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Student;
import com.internship.system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(
            @RequestBody Student student) {

        String message =
                studentService.saveStudent(student);

        return new ResponseEntity<>(
                message,
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(
            @PathVariable UUID id) {

        Optional<Student> student =
                studentService.getStudentById(id);

        if (student.isEmpty()) {

            return new ResponseEntity<>(
                    "The student with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                student.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable UUID id,
            @RequestBody Student student) {

        String message =
                studentService.updateStudent(
                        id,
                        student);

        if (message.equals("The student with that id does not exist")) {

            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable UUID id) {

        String message =
                studentService.deleteStudent(id);

        if (message.equals(
                "The student with that id does not exist")) {

            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(message);
    }
    @GetMapping("/by-last-name")
    public ResponseEntity<?> getStudentsByLastName(

        @RequestParam String lastName) {

                return ResponseEntity.ok(
                 studentService.getStudentsByLastName(lastName));
                
                }
}