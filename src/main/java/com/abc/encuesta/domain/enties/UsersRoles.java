package com.abc.encuesta.domain.enties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UsersRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Aquí va la relación de muchos a muchos entre Users y Roles
    @ManyToOne
    private Users users;

    @ManyToOne
    private Roles roles;
    //ola k ases

}
