package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.entities.CategoriesCatalog;

public interface ICategoryCatalog {
    //para encontrar//
    Optional<CategoriesCatalog> findById(Long id);

    //para guardar//
    CategoriesCatalog save(CategoriesCatalog categoriesCatalog);
    
    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<CategoriesCatalog> findAll();

    //para actualizar//
    CategoriesCatalog update(Long id, CategoriesCatalog categoriesCatalog);

}
