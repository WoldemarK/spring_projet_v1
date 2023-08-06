package com.example.springprojetv1.repository;

import com.example.springprojetv1.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from Company c where c.name = :name")
    Optional<Company> findByNameContainingIgnoreCase(@Param("name") String name);

    List<Company> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
