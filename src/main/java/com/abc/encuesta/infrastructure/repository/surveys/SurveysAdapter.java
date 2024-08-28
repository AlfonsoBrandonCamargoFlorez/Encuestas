package com.abc.encuesta.infrastructure.repository.surveys;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.ISurveys; // Asegúrate de tener esta interfaz
import com.abc.encuesta.domain.entities.Surveys;

@Service
public class SurveysAdapter implements ISurveys {

    @Autowired
    private SurveysRepository surveysRepository;

    @Override
    public Surveys save(Surveys surveys) {
        return surveysRepository.save(surveys);
    }

    @Override
    public void deleteById(Long id) {
        surveysRepository.deleteById(id);
    }

    @Override
    public List<Surveys> findAll() {
        return surveysRepository.findAll();
    }

    @Override
    public Optional<Surveys> findById(Long id) {
        return surveysRepository.findById(id);
    }

    @Override
    public Surveys update(Long id, Surveys surveys) {
        return surveysRepository.findById(id)
            .map(existingSurveys -> {
                // Actualizar los campos relevantes
                existingSurveys.setName(surveys.getName());
                existingSurveys.setDescription(surveys.getDescription());
                // Asegúrate de actualizar otros campos si es necesario
                return surveysRepository.save(existingSurveys);
            })
            .orElse(null);
    }
}
