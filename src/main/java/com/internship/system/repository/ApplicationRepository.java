package com.internship.system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import com.internship.system.domain.Application;
import com.internship.system.domain.ApplicationStatus;
import com.internship.system.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {

    @Query("""
        SELECT a.status, COUNT(a)
        FROM Application a
        GROUP BY a.status
        """)
        
    List<Object[]> countApplicationsByStatus();

    @Modifying
    @Query("""
        UPDATE Application a
        SET a.status = com.internship.system.domain.ApplicationStatus.REJECTED
        WHERE a.company.id = :companyId
        AND a.applicationDate = :date
        AND a.status <> com.internship.system.domain.ApplicationStatus.ACCEPTED
        """)
    int cancelApplicationsForDay(
        @Param("companyId") UUID companyId,
        @Param("date") LocalDate date);

    List<Application>
    findByStatusOrderByApplicationDateAsc(ApplicationStatus status);

    List<Application>
    findByApplicationDateBetweenOrderByApplicationDateAsc(
        LocalDate start,
        LocalDate end);
            
            boolean existsByCompanyAndApplicationDateAndStatusNot(
                Company company,
                LocalDate applicationDate,
                ApplicationStatus status);
}