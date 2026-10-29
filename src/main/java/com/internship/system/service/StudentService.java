package com.internship.system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.system.domain.Student;
import com.internship.system.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String saveStudent(Student student) {

        studentRepository.save(student);

        return "Student saved successfully";
    }

    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(UUID id) {

        return studentRepository.findById(id);
    }

    public String updateStudent(
            UUID id,
            Student student) {

        Optional<Student> existingStudent =
                studentRepository.findById(id);

        if (existingStudent.isEmpty()) {

            return "The student with that id does not exist";
        }

        Student dbStudent =
                existingStudent.get();

        dbStudent.setFirstName(
                student.getFirstName());

        dbStudent.setLastName(
                student.getLastName());

        dbStudent.setRegNumber(
                student.getRegNumber());

        dbStudent.setEmail(
                student.getEmail());

        dbStudent.setProgramme(
                student.getProgramme());

        studentRepository.save(
                dbStudent);

        return "Student updated successfully";
    }

    public String deleteStudent(UUID id) {

        if (!studentRepository.existsById(id)) {

            return "The student with that id does not exist";
        }

        studentRepository.deleteById(id);

        return "Student deleted successfully";
    }

    public List<Student> getStudentsByLastName(
            String lastName) {

        return studentRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(
                        lastName);
    }
}