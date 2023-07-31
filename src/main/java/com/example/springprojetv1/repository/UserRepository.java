package com.example.springprojetv1.repository;

import com.example.springprojetv1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u " +
            "where u.firstname like %:firstname% " +
            "and u.lastname like %:lastname%")
    List<User> findAllByFirstnameAndLastnameContaining(String firstname, String lastname);

    @Query("SELECT u FROM User u WHERE  u.username =:username")
    List<User> findAllByUsername(String username);
}
