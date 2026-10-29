package com.internship.system.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Application;
import com.internship.system.domain.ApplicationStatus;
import com.internship.system.service.ApplicationService;
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
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/save")
    public ResponseEntity<?> saveApplication(
            @RequestBody Application application) {

        String message =
                applicationService.saveApplication(
                        application);

        if (message.equals(
                "The student with that id does not exist")
                ||
                message.equals(
                "The company with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                message,
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllApplications() {

        return ResponseEntity.ok(
                applicationService.getAllApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getApplicationById(
            @PathVariable UUID id) {

        Optional<Application> application =
                applicationService.getApplicationById(id);

        if (application.isEmpty()) {

            return new ResponseEntity<>(
                    "The application with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                application.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateApplication(
            @PathVariable UUID id,
            @RequestBody Application application) {

        String message =
                applicationService.updateApplication(
                        id,
                        application);

        if (message.equals(
                "The application with that id does not exist")
                ||
                message.equals(
                "The student with that id does not exist")
                ||
                message.equals(
                "The company with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteApplication(
            @PathVariable UUID id) {

        String message =
                applicationService.deleteApplication(id);

        if (message.equals(
                "The application with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                message);
    }

    @GetMapping("/by-status")
    public ResponseEntity<?> getApplicationsByStatus(
            @RequestParam ApplicationStatus status) {

        return ResponseEntity.ok(
                applicationService.getApplicationsByStatus(
                        status));
    }
    @GetMapping("/between")
    public ResponseEntity<?> getApplicationsBetweenDates(
            @RequestParam String start,
            @RequestParam String end) {

        LocalDate startDate =
                LocalDate.parse(start);

        LocalDate endDate =
                LocalDate.parse(end);

        return ResponseEntity.ok(
                applicationService.getApplicationsBetweenDates(startDate,endDate));
        }
}