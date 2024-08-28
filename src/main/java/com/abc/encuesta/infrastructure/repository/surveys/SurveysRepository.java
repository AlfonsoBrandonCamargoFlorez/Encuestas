package com.abc.encuesta.infrastructure.repository.surveys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.encuesta.domain.entities.Surveys;

public interface SurveysRepository extends JpaRepository<Surveys,Long> {

}
