package com.internship.system.repository;

import java.util.UUID;
import com.internship.system.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, UUID>{
    
}