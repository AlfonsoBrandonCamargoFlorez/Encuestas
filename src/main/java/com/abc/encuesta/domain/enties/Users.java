package com.abc.encuesta.domain.enties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
