package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.entities.Surveys;

public interface ISurveys {
    //para encontrar//
    Optional<Surveys> findById(Long id);
    
    //para guardar//
    Surveys save(Surveys surveys);

    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<Surveys> findAll();

    //para actualizar//
    Surveys update(Long id, Surveys surveys);

}
