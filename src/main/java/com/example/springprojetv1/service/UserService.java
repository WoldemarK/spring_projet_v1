package com.example.springprojetv1.service;

import com.example.springprojetv1.model.User;
import com.example.springprojetv1.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllByFirstnameAndLastnameContaining(String firstname, String lastname) {
        log.info("Поиск по :" + firstname + " " + lastname);
        return userRepository.findAllByFirstnameAndLastnameContaining(firstname, lastname);
    }

    public List<User> findAllByUsername(String username) {
        log.info("Поиск по :" + username);
        return userRepository.findAllByUsername(username);
    }

    @Transactional
    public int updateRole(User.Role role, Long ids) {
        log.info("Обновление по :" + role + " " + ids);
        if (role != null && ids != null) {
            return userRepository.updateRole(role, ids);
        }
        return -1;
    }

    public Optional<User> findTopByOrOrderByIdDesc() {
        return findTopByOrOrderByIdDesc();
    }
}
