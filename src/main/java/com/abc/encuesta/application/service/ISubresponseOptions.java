package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.enties.SubresponseOptions;

public interface ISubresponseOptions {
    //para encontrar//
    Optional<SubresponseOptions> findByid(Long id);

    //para guardar//
    SubresponseOptions save(SubresponseOptions subresponseOptions);

    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<SubresponseOptions> findAll();

    //para actualizar//
    SubresponseOptions update(Long id, SubresponseOptions subresponseOptions);

}
