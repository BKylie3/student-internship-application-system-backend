package com.internship.system.repository;

import java.util.List;
import java.util.UUID;
import com.internship.system.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

  @Query("""
            SELECT DISTINCT c
            FROM Company c
            JOIN c.fields f
            WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%'))
  """)

  List<Company> findCompaniesByFieldName(
    @Param("name") String name);

}