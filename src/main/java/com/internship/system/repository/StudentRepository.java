package com.internship.system.repository;

import java.util.List;
import java.util.UUID;
import com.internship.system.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student>
    findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("""
        SELECT DISTINCT a.student
        FROM Application a
        WHERE a.company.id = :companyId
        """)
        List<Student> findStudentsOfCompany(
            @Param("companyId")UUID companyId);

}