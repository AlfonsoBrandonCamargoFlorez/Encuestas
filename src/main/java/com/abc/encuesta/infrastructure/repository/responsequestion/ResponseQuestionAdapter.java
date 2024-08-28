package com.abc.encuesta.infrastructure.repository.responsequestion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.IResponseQuestion;

import com.abc.encuesta.domain.entities.ResponseQuestion;



@Service
public class ResponseQuestionAdapter implements IResponseQuestion {

    @Autowired
    private ResponseQuestionRepository responseQuestionRepository;

    @Override
    public Optional<ResponseQuestion> findById(Long id) {
        return responseQuestionRepository.findById(id);
    }

    @Override
    public ResponseQuestion save(ResponseQuestion responseQuestion) {
        return responseQuestionRepository.save(responseQuestion);
    }

    @Override
    public void deleteById(Long id) {
        responseQuestionRepository.deleteById(id);
    }

    @Override
    public List<ResponseQuestion> findAll() {
        return responseQuestionRepository.findAll();
    }

    @Override
    public ResponseQuestion update(Long id, ResponseQuestion responseQuestion) {
        return responseQuestionRepository.findById(id)
                .map(existingResponseQuestion -> {
                    // Actualizar los campos relevantes
                    existingResponseQuestion.setResponsetext(responseQuestion.getResponsetext());
                                      

                    return responseQuestionRepository.save(existingResponseQuestion);
                })
                .orElse(null);
    }
}
