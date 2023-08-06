package com.example.springprojetv1.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Builder.Default
    @ElementCollection
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    @CollectionTable(name = "company_locales", joinColumns = @JoinColumn(name = "company_id"))
    private Map<String, String> locales = new HashMap<>();

}
