package com.abc.encuesta.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CategoriesCatalog {
    //aqui genero la llave primaria de la tabla
    @Id
    //con esto puede que haga el autoincrement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded 
    Audit audit = new Audit();
    //defino el tipo del variable no va vacio y es varchar de 255
    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;
}
