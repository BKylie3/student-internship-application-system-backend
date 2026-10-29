package com.internship.system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import com.internship.system.domain.Application;
import com.internship.system.domain.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {

    List<Application>
    findByStatusOrderByApplicationDateAsc(ApplicationStatus status);

    List<Application>
    findByApplicationDateBetweenOrderByApplicationDateAsc(
        LocalDate start,
        LocalDate end);
}