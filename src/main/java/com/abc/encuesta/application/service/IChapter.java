package com.abc.encuesta.application.service;

import java.util.Optional;


import java.util.List;
import com.abc.encuesta.domain.enties.Chapter;

public interface IChapter {
    //para encontrar//
    Optional<Chapter> findById(Long id);

    //para guardar//
    Chapter save(Chapter chapter);

    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<Chapter> findAll();

    //para actualizar//
    Chapter update(Long id, Chapter chapter);

}
