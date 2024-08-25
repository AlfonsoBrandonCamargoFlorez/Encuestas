package com.abc.encuesta.domain.enties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ResponseQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ResponseOptions responseOptions;

    @ManyToOne
    private SubresponseOptions subResponseOptions;

    @Column (columnDefinition = "VARCHAR(80)", nullable = false)
    private String responsetext;

}
