package com.internship.system.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.internship.system.domain.Application;
import com.internship.system.domain.Placement;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, UUID> {

    @Query("""
            SELECT p
            FROM Placement p
            WHERE p.application.student.regNumber = :regNumber
            ORDER BY p.recordedDate DESC
            """)
    List<Placement> findPlacementsByStudentRegNumber(
            @Param("regNumber") String regNumber);

    boolean existsByApplication(
            Application application);
    
}
