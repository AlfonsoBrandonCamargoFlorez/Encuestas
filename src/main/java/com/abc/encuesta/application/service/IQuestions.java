package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.entities.Questions;

public interface IQuestions {
    //para encontrar//
    Optional<Questions> findById(Long id);

    //para guardar//
    Questions save(Questions questions);

    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<Questions> findAll();
    
    //para actualizar//
    Questions update(Long id, Questions questions);

}
