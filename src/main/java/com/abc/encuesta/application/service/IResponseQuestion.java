package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.entities.ResponseQuestion;

public interface IResponseQuestion {
    //para encontrar//
    Optional<ResponseQuestion> findById(Long id);

    //para guardar//
    ResponseQuestion save(ResponseQuestion responseQuestion);

    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<ResponseQuestion> findAll();

    //para actualizar//
    ResponseQuestion update(Long id, ResponseQuestion responseQuestion);

}
