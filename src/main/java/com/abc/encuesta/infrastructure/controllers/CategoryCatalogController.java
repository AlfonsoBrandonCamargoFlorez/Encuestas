package com.abc.encuesta.infrastructure.controllers;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.encuesta.application.service.ICategoryCatalog;
import com.abc.encuesta.domain.entities.CategoriesCatalog;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/categories")
public class CategoryCatalogController {
    @Autowired
    private ICategoryCatalog ICategoriesCatalog;

    @GetMapping
    public List<CategoriesCatalog> getCategories() {
        return ICategoriesCatalog.findAll();

    }
 

    @GetMapping("/{id}")
    public Optional<CategoriesCatalog> show(@PathVariable Long id) {
        return ICategoriesCatalog.findById(id);
    
    }
    
    @PostMapping
    public ResponseEntity<CategoriesCatalog> crearCategories(@RequestBody CategoriesCatalog categoriesCatalog) {
    
        ICategoriesCatalog.save(categoriesCatalog);
        return new ResponseEntity<>(categoriesCatalog, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    
    public ResponseEntity<CategoriesCatalog> actualizarCategories(@PathVariable Long id,
            @RequestBody CategoriesCatalog categoriesCatalog) {
    
        CategoriesCatalog categoriesUser = ICategoriesCatalog.update(id, categoriesCatalog);
        return new ResponseEntity<>(categoriesUser, HttpStatus.OK);
    };
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategories(@PathVariable Long id) {
        if (ICategoriesCatalog.findById(id).isPresent()) {
            ICategoriesCatalog.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    }
    

