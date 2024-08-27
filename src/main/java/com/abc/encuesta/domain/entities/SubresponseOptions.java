package com.abc.encuesta.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SubresponseOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    Audit audit = new Audit();

    @Column (columnDefinition = "VARCHAR(255)", nullable = false)
    private String component_html;

    @Column (columnDefinition = "VARCHAR(255)", nullable = false)
    private String subresponse_text;
    
    @ManyToOne
    private ResponseOptions responseOptions;

    



}
