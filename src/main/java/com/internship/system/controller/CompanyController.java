package com.internship.system.controller;

import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Company;
import com.internship.system.service.CompanyService;
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
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCompany(
            @RequestBody Company company) {

        String message =
                companyService.saveCompany(company);

        if (message.equals(
                "The booth with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                message,
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCompanies() {

        return ResponseEntity.ok(
                companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(
            @PathVariable UUID id) {

        Optional<Company> company =
                companyService.getCompanyById(id);

        if (company.isEmpty()) {

            return new ResponseEntity<>(
                    "The company with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                company.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCompany(
            @PathVariable UUID id,
            @RequestBody Company company) {

        String message =
                companyService.updateCompany(
                        id,
                        company);

        if (message.equals(
                "The company with that id does not exist")
                ||
                message.equals(
                "The booth with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompany(
            @PathVariable UUID id) {

        String message =
                companyService.deleteCompany(id);

        if (message.equals(
                "The company with that id does not exist")) {

            return new ResponseEntity<>(
                    message,
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(message);

    }
}