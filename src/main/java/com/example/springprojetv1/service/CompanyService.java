package com.example.springprojetv1.service;

import com.example.springprojetv1.model.Company;
import com.example.springprojetv1.repository.CompanyRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        return //Optional.ofNullable(companyRepository.findById(id).orElseThrow(
                // () -> new RuntimeException("По данному ID: " + id + " не чего не найдено")));
                companyRepository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Удаление компании по ID: " + id);
        companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("По данному ID: " + id + " не чего не найдено"));
    }

    @Transactional
    public Optional<Company> createNewCompany(Company company) {
        log.info("Процесс создания и сохранения" + company.getName());
        return Optional.ofNullable(Optional.of(companyRepository.save(company)).orElseThrow(
                () -> new RuntimeException("Компания не создана: " + company.getName())));
    }

    public List<Company> companies() {
        log.info("Вывод всех компаний");
        return companyRepository.findAll();
    }

    public Optional<Company> findByName(String name) {
        log.info("Поиск компании по названию : " + name);
        Objects.requireNonNull(name, "Компания с запрашиваемым именем нет в списке: " + name);
        return companyRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Company> findAllByNameContainingIgnoreCase(String name) {
        Objects.requireNonNull(name, "Компании с запрашиваемым именем нет в списке: " + name);
        return companyRepository.findAllByNameContainingIgnoreCase(name);
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public void save(Company company) {
        if (company == null) {
            throw new RuntimeException("Error save company: ");
        }
        companyRepository.save(company);
    }
}
