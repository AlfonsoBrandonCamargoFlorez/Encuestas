package com.abc.encuesta.infrastructure.repository.responsequestion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.encuesta.domain.entities.ResponseQuestion;

public interface ResponseQuestionRepository extends JpaRepository<ResponseQuestion, Long>{

}
