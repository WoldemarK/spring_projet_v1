package com.example.springprojetv1.service;

import com.example.springprojetv1.model.Company;
import com.example.springprojetv1.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Commit
@Transactional
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CompanyServiceTest {
    private static final Long ID = 1L;

    private final EntityManager entityManager;
    private final TransactionTemplate template;
    @Mock
    private final CompanyRepository companyRepository;
    @InjectMocks
    private final CompanyService companyService;
    @Test
    void getAllCompany() {
        List<Company> allCompany = companyService.getAllCompany();
        System.out.println(allCompany);
        assertFalse(allCompany.isEmpty(), "Company list should be empty");
    }

    @Test
    void delete() {
        Optional<Company> company = companyRepository.findById(ID);
        assertTrue(company.isPresent());
        company.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(ID).isEmpty());

    }


    @Test
    void findById() {
        template.executeWithoutResult(tx -> {
            Company company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            assertThat(company.getLocales()).hasSize(2);
        });
    }

    @Test
    @Rollback(value = false)
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