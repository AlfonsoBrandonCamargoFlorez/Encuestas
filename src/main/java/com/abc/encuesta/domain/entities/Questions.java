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
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Chapter chapter;

    @Embedded
    Audit audit = new Audit();

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String question_number;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String response_type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment_question;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String question_text;
}
