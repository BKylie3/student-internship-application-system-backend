package com.internship.system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.internship.system.domain.Application;
import com.internship.system.domain.ApplicationStatus;
import com.internship.system.domain.Company;
import com.internship.system.domain.Student;
import com.internship.system.repository.ApplicationRepository;
import com.internship.system.repository.CompanyRepository;
import com.internship.system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public String saveApplication(Application application) {

        if (application.getStudent() == null
                || application.getStudent().getId() == null) {

            return "The student with that id does not exist";
        }

        if (application.getCompany() == null
                || application.getCompany().getId() == null) {

            return "The company with that id does not exist";
        }

        Optional<Student> student =
                studentRepository.findById(
                        application.getStudent().getId());

        if (student.isEmpty()) {

            return "The student with that id does not exist";
        }

        Optional<Company> company =
                companyRepository.findById(
                        application.getCompany().getId());

        if (company.isEmpty()) {

            return "The company with that id does not exist";
        }

        application.setStudent(student.get());
        application.setCompany(company.get());

        applicationRepository.save(application);

        return "Application saved successfully";
    }

    public List<Application> getAllApplications() {

        return applicationRepository.findAll();
    }

    public Optional<Application> getApplicationById(
            UUID id) {

        return applicationRepository.findById(id);
    }

    public String updateApplication(
            UUID id,
            Application application) {

        Optional<Application> existingApplication =
                applicationRepository.findById(id);

        if (existingApplication.isEmpty()) {

            return "The application with that id does not exist";
        }

        Application dbApplication =
                existingApplication.get();

        dbApplication.setApplicationDate(
                application.getApplicationDate());

        dbApplication.setPositionTitle(
                application.getPositionTitle());

        dbApplication.setCoverLetter(
                application.getCoverLetter());

        if (application.getStudent() != null
                && application.getStudent().getId() != null) {

            Optional<Student> student =
                    studentRepository.findById(
                            application.getStudent().getId());

            if (student.isEmpty()) {

                return "The student with that id does not exist";
            }

            dbApplication.setStudent(student.get());
        }

        if (application.getCompany() != null
                && application.getCompany().getId() != null) {

            Optional<Company> company =
                    companyRepository.findById(
                            application.getCompany().getId());

            if (company.isEmpty()) {

                return "The company with that id does not exist";
            }

            dbApplication.setCompany(company.get());
        }

        applicationRepository.save(dbApplication);

        return "Application updated successfully";
    }

    public String deleteApplication(UUID id) {

        if (!applicationRepository.existsById(id)) {

            return "The application with that id does not exist";
        }

        applicationRepository.deleteById(id);

        return "Application deleted successfully";
    }

    public List<Application> getApplicationsByStatus(
            ApplicationStatus status) {

        return applicationRepository.findByStatusOrderByApplicationDateAsc(status);
            }
            public List<Application> getApplicationsBetweenDates(
                LocalDate start,
                LocalDate end){
                    return applicationRepository.findByApplicationDateBetweenOrderByApplicationDateAsc(start, end);
                }
        }
