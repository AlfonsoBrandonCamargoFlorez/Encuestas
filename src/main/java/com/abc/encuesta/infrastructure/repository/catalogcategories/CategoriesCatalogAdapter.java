package com.abc.encuesta.infrastructure.repository.catalogcategories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.ICategoryCatalog;
import com.abc.encuesta.domain.entities.Audit;
import com.abc.encuesta.domain.entities.CategoriesCatalog;

import java.time.LocalDateTime;

@Service
public class CategoriesCatalogAdapter implements ICategoryCatalog{
    @Autowired
    private CategoriesCatalogRepository categoriesCatalogRepository;

    @Override
    public Optional<CategoriesCatalog> findById(Long id) {
       return categoriesCatalogRepository.findById(id);
    }

    @Override
    public CategoriesCatalog save(CategoriesCatalog categoriesCatalog) {
    return categoriesCatalogRepository.save(categoriesCatalog);
    }

    @Override
    public void deleteById(Long id) {
        categoriesCatalogRepository.deleteById(id);
        
    }

    @Override
    public List<CategoriesCatalog> findAll() {
       return categoriesCatalogRepository.findAll();
    }

    @Override
    public CategoriesCatalog update(Long id, CategoriesCatalog categoriesCatalog) {
        return categoriesCatalogRepository.findById(id)
        .map(existingCategories -> {
            // Mantener el createdAt existente
            existingCategories.setName(categoriesCatalog.getName());

            // Actualizar el campo updatedAt en el audit
            Audit audit = existingCategories.getAudit();
            audit.setUpdatedAt(LocalDateTime.now());
            existingCategories.setAudit(audit);

            return categoriesCatalogRepository.save(existingCategories);
        })
        .orElse(null); 
    }

}   
