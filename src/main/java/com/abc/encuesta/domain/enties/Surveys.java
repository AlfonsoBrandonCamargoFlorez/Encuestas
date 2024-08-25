package com.abc.encuesta.domain.enties;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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


}
