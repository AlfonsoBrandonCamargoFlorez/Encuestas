package com.abc.encuesta.application.service;

import com.abc.encuesta.domain.enties.CategoriesCatalog;
import java.util.List;
import java.util.Optional;

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
