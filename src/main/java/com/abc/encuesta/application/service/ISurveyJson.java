package com.abc.encuesta.application.service;

import java.util.List;
import java.util.Optional;

import com.abc.encuesta.domain.enties.SurveyJson;

public interface ISurveyJson {
    //para encontrar//
    Optional<SurveyJson> findByid(Long id);

    //para guardar//
    SurveyJson save(SurveyJson surveyJson);

    //para eliminar//
    void deleteById(Long id);

    //para listar todas//
    List<SurveyJson> findAll();

    //para actualizar//
    SurveyJson update(Long id, SurveyJson surveyJson);

}
