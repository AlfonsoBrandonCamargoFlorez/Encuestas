package com.abc.encuesta.infrastructure.repository.subresponseOptions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.ISubresponseOptions; // Asegúrate de tener esta interfaz

import com.abc.encuesta.domain.entities.SubresponseOptions;

@Service
public class SubresponseOptionsAdapter implements ISubresponseOptions {

    @Autowired
    private SubresponseOptionsRepository subresponseOptionsRepository;

    @Override
    public SubresponseOptions save(SubresponseOptions subresponseOptions) {
        return subresponseOptionsRepository.save(subresponseOptions);
    }

    @Override
    public void deleteById(Long id) {
        subresponseOptionsRepository.deleteById(id);
    }

    @Override
    public List<SubresponseOptions> findAll() {
        return subresponseOptionsRepository.findAll();
    }

    @Override
    public Optional<SubresponseOptions> findById(Long id) {
        return subresponseOptionsRepository.findById(id);
    }

    @Override
    public SubresponseOptions update(Long id, SubresponseOptions subresponseOptions) {
        return subresponseOptionsRepository.findById(id)
            .map(existingSubresponseOptions -> {
                // Actualizar los campos relevantes
                existingSubresponseOptions.setComponent_html(subresponseOptions.getComponent_html());
                existingSubresponseOptions.setSubresponse_text(subresponseOptions.getSubresponse_text());
                // Asegúrate de actualizar otros campos si es necesario
                return subresponseOptionsRepository.save(existingSubresponseOptions);
            })
            .orElse(null);
    }
}
