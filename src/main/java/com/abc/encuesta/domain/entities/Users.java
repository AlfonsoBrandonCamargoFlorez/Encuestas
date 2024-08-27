package com.abc.encuesta.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (columnDefinition = "VARCHAR(12)",nullable = false)
    private String username;

    @Column (columnDefinition = "VARCHAR(255)",nullable = false)
    private String password;

    @Column (columnDefinition = "BOOL",nullable = false)
    private Boolean enabled;

}
