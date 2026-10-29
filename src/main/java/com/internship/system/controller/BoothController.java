package com.internship.system.controller;

import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Booth;
import com.internship.system.service.BoothService;
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
@RequestMapping("/api/booths")
public class BoothController {

    @Autowired
    private BoothService boothService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBooth(
            @RequestBody Booth booth) {

        String message =
                boothService.saveBooth(booth);

        return new ResponseEntity<>(
                message,
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooths() {

        return ResponseEntity.ok(
                boothService.getAllBooths());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoothById(
            @PathVariable UUID id) {

        Optional<Booth> booth =
                boothService.getBoothById(id);

        if (booth.isEmpty()) {

            return new ResponseEntity<>(
                    "The booth with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                booth.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBooth(
            @PathVariable UUID id,
            @RequestBody Booth booth) {

        String message =
                boothService.updateBooth(
                        id,
                        booth);

        if (message.equals(
                "The booth with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooth(
            @PathVariable UUID id) {

        String message =
                boothService.deleteBooth(id);

        if (message.equals(
                "The booth with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(message);
}
       
        @GetMapping("/free")
    public ResponseEntity<?> getFreeBooths() {

        return ResponseEntity.ok(
                boothService.getFreeBooths());
    }
}