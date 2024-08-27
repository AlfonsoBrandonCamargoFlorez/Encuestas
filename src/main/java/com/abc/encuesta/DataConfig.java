package com.abc.encuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abc.encuesta.domain.entities.CategoriesCatalog;
import com.abc.encuesta.infrastructure.repository.catalogcategories.CategoriesCatalogRepository;


@Configuration
public class DataConfig {

    @Autowired
    private CategoriesCatalogRepository categoriesCatalogRepository;
    @Bean
    public CommandLineRunner initData() {
        return args -> {

       CategoriesCatalog generalCategories = new CategoriesCatalog();
        generalCategories.setName("General");
        categoriesCatalogRepository.save(generalCategories);

        CategoriesCatalog productFeedbackCategories = new CategoriesCatalog();
        productFeedbackCategories.setName("Product Feedback");
        categoriesCatalogRepository.save(productFeedbackCategories);
    // Otros repositorios...

 

        };
    }
}