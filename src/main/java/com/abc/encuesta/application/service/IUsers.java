package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.entities.Users;

public interface IUsers {
    //para encontrar//
    Optional<Users> findByid(Long id);

    //para guardar//
    Users save(Users users);

    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<Users> findAll();
    
    //para actualizar//
    Users update(Long id, Users users);

}
