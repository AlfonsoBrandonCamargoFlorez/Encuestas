package com.abc.encuesta.application.service;

import java.util.Optional;

import com.abc.encuesta.domain.entities.Chapter;

import java.util.List;

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
