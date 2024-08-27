package com.abc.encuesta.infrastructure.repository.questions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.IQuestions;
import com.abc.encuesta.domain.entities.Audit;
import com.abc.encuesta.domain.entities.Questions;

import java.time.LocalDateTime;

@Service
public class QuestionsAdapter implements IQuestions {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Override
    public Optional<Questions> findById(Long id) {
        return questionsRepository.findById(id);
    }

    @Override
    public Questions save(Questions question) {
        return questionsRepository.save(question);
    }

    @Override
    public void deleteById(Long id) {
        questionsRepository.deleteById(id);
    }

    @Override
    public List<Questions> findAll() {
        return questionsRepository.findAll();
    }

    @Override
    public Questions update(Long id, Questions question) {
        return questionsRepository.findById(id)
                .map(existingQuestion -> {
                    // Mantener el createdAt existente
                    existingQuestion.setQuestion_text(question.getQuestion_text());

                    // Actualizar el campo updatedAt en el audit
                    Audit audit = existingQuestion.getAudit();
                    audit.setUpdatedAt(LocalDateTime.now());
                    existingQuestion.setAudit(audit);

                    return questionsRepository.save(existingQuestion);
                })
                .orElse(null);
    }
}
