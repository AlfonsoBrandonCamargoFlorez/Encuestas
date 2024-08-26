package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.enties.ResponseOptions;

public interface IResponseOptions {
    //para encontrar//
    Optional<ResponseOptions> findById(Long id);

    //para guardar//
    ResponseOptions save(ResponseOptions responseOptions);

    //para eliminar//
    void deleteById(Long id);
    
    //para listar todas//
    List<ResponseOptions> findAll();
    
    //para actualizar//
    ResponseOptions update(Long id, ResponseOptions responseOptions);

}
