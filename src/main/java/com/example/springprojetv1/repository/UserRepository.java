package com.example.springprojetv1.repository;

import com.example.springprojetv1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u " +
            "where u.firstname like %:firstname% " +
            "and u.lastname like %:lastname%")
    List<User> findAllByFirstnameAndLastnameContaining(@Param("firstname") String firstname,
                                                       @Param("lastname") String lastname);

    @Query("SELECT u FROM User u WHERE  u.username =:username")
    List<User> findAllByUsername(@Param("username") String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u set u.role=:role where u.id in (:ids)")
    int updateRole(User.Role role, Long... ids);

   // Optional<User> findTopByOrOrderByIdDesc();

   // List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    @EntityGraph
    @Query(value = "select u from User u",
            countQuery = "select count (distinct u.firstname)from User u")
    Page<User> findAllBy(Pageable pageable);
}
