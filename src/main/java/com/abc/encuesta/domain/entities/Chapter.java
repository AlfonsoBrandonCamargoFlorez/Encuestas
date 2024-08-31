package com.abc.encuesta.domain.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded 
    Audit audit = new Audit();

    @ManyToOne
    private Surveys surveys;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String chapter_title;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String chapter_number;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita el ciclo infinito en la serialización JSON
    private Set<Questions> questions;
}
