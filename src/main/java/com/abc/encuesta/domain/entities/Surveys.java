package com.abc.encuesta.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
public class Surveys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    Audit audit = new Audit();

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String description;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

   @OneToMany(mappedBy = "surveys", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita el ciclo infinito en la serialización JSON
    private Set<Chapter> chapters;
}
