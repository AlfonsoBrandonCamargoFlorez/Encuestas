package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.entities.UsersRoles;

public interface IUsersRoles {
    //para encontrar//
    Optional<UsersRoles> findById(Long id);

    //para guardar//
    UsersRoles save(UsersRoles usersRoles);

    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<UsersRoles> findAll();
    
    //para actualizar//
    UsersRoles update(Long id, UsersRoles usersRoles);
    

}
