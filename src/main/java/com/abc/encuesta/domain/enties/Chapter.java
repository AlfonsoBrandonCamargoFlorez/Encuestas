package com.abc.encuesta.domain.enties;

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
}
