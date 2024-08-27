package com.abc.encuesta.infrastructure.repository.questions;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.encuesta.domain.entities.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
