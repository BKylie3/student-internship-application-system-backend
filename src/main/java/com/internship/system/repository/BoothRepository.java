package com.internship.system.repository;

import java.util.List;
import java.util.UUID;
import com.internship.system.domain.Booth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothRepository extends JpaRepository<Booth, UUID> {

    @Query("""
            SELECT b
            FROM Booth b
            WHERE NOT EXISTS (
                SELECT c
                FROM Company c
                WHERE c.booth = b
            )
            ORDER BY b.boothNumber ASC
            """)
            List<Booth> findFreeBooths();
            
}