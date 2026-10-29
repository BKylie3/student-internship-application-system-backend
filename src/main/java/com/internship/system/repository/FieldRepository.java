package com.internship.system.repository;

import java.util.List;
import java.util.UUID;
import com.internship.system.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, UUID>{
    
    @Query("""
        SELECT f
    FROM Field f
    WHERE f.companies IS EMPTY
        """)

    List<Field> findUnusedFields();
}