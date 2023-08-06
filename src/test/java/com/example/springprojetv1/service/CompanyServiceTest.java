package com.example.springprojetv1.service;

import com.example.springprojetv1.model.Company;
import com.example.springprojetv1.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@Commit
@Transactional
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CompanyServiceTest {

    private final EntityManager entityManager;
    private final TransactionTemplate template;
    private final CompanyRepository companyRepository;

    @Test
    void delete() {
        Optional<Company> company = companyRepository.findById(1L);
        assertTrue(company.isPresent());
        company.ifPresent(companyRepository::delete);
        entityManager.flush();
    }


    @Test
    void findById() {
        template.executeWithoutResult(tx -> {
            Company company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            Assertions.assertThat(company.getLocales()).hasSize(2);
        });
    }

    @Test
  //  @Rollback(value = false)
    void save() {
        Company company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                )).build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }

}