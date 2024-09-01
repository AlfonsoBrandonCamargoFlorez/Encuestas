package com.abc.encuesta.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ResponseQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ResponseOptions responseOptions;

    @ManyToOne
    private SubresponseOptions subResponseOptions;

    @Column(columnDefinition = "VARCHAR(80)", nullable = false)
    private String responsetext;

    @ManyToOne
    @JoinColumn(name = "questions_id", nullable = false) // Especificar columna de unión
    private Questions questions;

}
