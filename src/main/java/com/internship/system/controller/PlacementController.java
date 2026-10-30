package com.internship.system.controller;

import java.util.UUID;
import com.internship.system.domain.Placement;
import com.internship.system.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/placements")
public class PlacementController {

    @Autowired
    private PlacementService placementService;

    @PostMapping("/save")
    public ResponseEntity<?> savePlacement(
            @RequestParam UUID applicationId,
            @RequestBody Placement placement) {

        String message =
                placementService.savePlacement(
                        applicationId,
                        placement);

        if (message.equals(
                "The application with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        if (message.equals(
                "Placements are only for ACCEPTED applications")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                message,
                HttpStatus.CREATED);
    }

    @GetMapping("/student/{regNumber}")
    public ResponseEntity<?> getPlacementsByStudent(
            @PathVariable String regNumber) {

        return ResponseEntity.ok(
                placementService.getPlacementsByStudent(
                        regNumber));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlacement(
            @PathVariable UUID id) {

        String message =
                placementService.deletePlacement(id);

        if (message.equals(
                "The placement with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                message);
    }
}
