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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ResponseOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String option_value;

    @Embedded
    Audit audit = new Audit();

    @ManyToOne
    @JoinColumn(name = "questions_id", nullable = false) // Especificar columna de unión
    private Questions questions;

    @ManyToOne
    private ResponseOptions responseOptions;

    @ManyToOne
    private CategoriesCatalog categories;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String typecomponenthtml;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment_response;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String option_text;

    @OneToMany(mappedBy = "responseOptions", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<SubresponseOptions> subresponseOptions;

}
