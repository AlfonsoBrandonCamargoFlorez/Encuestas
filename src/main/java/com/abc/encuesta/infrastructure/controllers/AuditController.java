package com.abc.encuesta.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.encuesta.application.service.ICategoryCatalog;
import com.abc.encuesta.domain.entities.CategoriesCatalog;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    @Autowired
    private ICategoryCatalog categoryCatalogService;

    @GetMapping
    public ResponseEntity<List<CategoriesCatalog>> getAllCategories() {
        List<CategoriesCatalog> categories = categoryCatalogService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriesCatalog> getCategoryById(@PathVariable Long id) {
        Optional<CategoriesCatalog> category = categoryCatalogService.findById(id);
        return category.map(ResponseEntity::ok)
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CategoriesCatalog> createCategory(@RequestBody CategoriesCatalog categoriesCatalog) {
        CategoriesCatalog createdCategory = categoryCatalogService.save(categoriesCatalog);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriesCatalog> updateCategory(@PathVariable Long id,
                                                             @RequestBody CategoriesCatalog categoriesCatalog) {
        CategoriesCatalog updatedCategory = categoryCatalogService.update(id, categoriesCatalog);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory)
                                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryCatalogService.findById(id).isPresent()) {
            categoryCatalogService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
