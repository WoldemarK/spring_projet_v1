package com.example.springprojetv1.service;

import com.example.springprojetv1.model.Company;
import com.example.springprojetv1.repository.CompanyRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @PostConstruct
    private void init() {
        log.info("Начальная загрузка init method class CompanyService");
    }

    public Optional<Company> findById(Long id) {
        log.info("Производится поиск по ID: " + id);
        return Optional.ofNullable(companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("По данному ID: " + id + " не чего не найдено")));
    }

   // @Transactional
    public void delete(Long id) {
        companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("По данному ID: " + id + " не чего не найдено"));
    }

  //  @Transactional
    public Optional<Company> createNewCompany(Company company) {
        log.info("Процесс создания и сохранения" + company.getName());
        return Optional.ofNullable(Optional.of(companyRepository.save(company)).orElseThrow(
                () -> new RuntimeException("Компания не создана: " + company.getName())));
    }
}
