package com.abc.encuesta.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.encuesta.application.service.IQuestions; // Asegúrate de que esta interfaz exista
import com.abc.encuesta.domain.entities.Questions;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    @Autowired
    private IQuestions questionsService; // Inyectar el servicio correcto

    @GetMapping
    public ResponseEntity<List<Questions>> getAllQuestions() {
        List<Questions> questions = questionsService.findAll();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questions> getQuestionById(@PathVariable Long id) {
        Optional<Questions> question = questionsService.findById(id);
        return question.map(ResponseEntity::ok)
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Questions> createQuestion(@RequestBody Questions question) {
        Questions createdQuestion = questionsService.save(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Questions> updateQuestion(@PathVariable Long id, @RequestBody Questions question) {
        Questions updatedQuestion = questionsService.update(id, question);
        return updatedQuestion != null ? ResponseEntity.ok(updatedQuestion)
                                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        if (questionsService.findById(id).isPresent()) {
            questionsService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
