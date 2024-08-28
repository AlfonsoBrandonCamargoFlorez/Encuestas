package com.abc.encuesta.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.encuesta.application.service.ISurveys; // Asegúrate de que esta interfaz exista
import com.abc.encuesta.domain.entities.Surveys;

@RestController
@RequestMapping("/api/surveys")
public class SurveysController {

    @Autowired
    private ISurveys surveysService; // Inyectar el servicio correcto

    @GetMapping
    public ResponseEntity<List<Surveys>> getAllSurveys() {
        List<Surveys> surveysList = surveysService.findAll();
        return new ResponseEntity<>(surveysList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Surveys> getSurveyById(@PathVariable Long id) {
        Optional<Surveys> survey = surveysService.findById(id);
        return survey.map(ResponseEntity::ok)
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Surveys> createSurvey(@RequestBody Surveys survey) {
        Surveys createdSurvey = surveysService.save(survey);
        return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Surveys> updateSurvey(@PathVariable Long id, @RequestBody Surveys survey) {
        Surveys updatedSurvey = surveysService.update(id, survey);
        return updatedSurvey != null ? ResponseEntity.ok(updatedSurvey)
                                     : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        if (surveysService.findById(id).isPresent()) {
            surveysService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
