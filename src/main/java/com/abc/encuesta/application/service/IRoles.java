package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.entities.Roles;

public interface IRoles {
    //para encontrar//
    Optional<Roles> findById(Long id);

    //para guardar//
    Roles save(Roles roles);

    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<Roles> findAll();

    Roles updade(Long id, Roles roles);

}
