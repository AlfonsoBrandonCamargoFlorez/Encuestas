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
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false) // Especificar columna de unión
    private Chapter chapter;

    @Embedded
    private Audit audit = new Audit();

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String question_number;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String response_type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment_question;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String question_text;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ResponseQuestion> responseQuestions;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ResponseOptions> responseOptions;
}
