package com.internship.system.controller;

import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Field;
import com.internship.system.service.FieldService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping("/save")
    public ResponseEntity<?> saveField(
            @RequestBody Field field) {

        String message =
                fieldService.saveField(field);

        return new ResponseEntity<>(
                message,
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllFields() {

        return ResponseEntity.ok(
                fieldService.getAllFields());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFieldById(
            @PathVariable UUID id) {

        Optional<Field> field =
                fieldService.getFieldById(id);

        if (field.isEmpty()) {

            return new ResponseEntity<>(
                    "The field with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                field.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateField(
            @PathVariable UUID id,
            @RequestBody Field field) {

        String message =
                fieldService.updateField(
                        id,
                        field);

        if (message.equals(
                "The field with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteField(
            @PathVariable UUID id) {

        String message =
                fieldService.deleteField(id);

        if (message.equals(
                "The field with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(message);
    }
    @GetMapping ("/unused")
    public ResponseEntity<?> getUnusedFields(){
        return ResponseEntity.ok(fieldService.getUnusedFields());
    }
}