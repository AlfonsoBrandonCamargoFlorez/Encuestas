package com.abc.encuesta.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.encuesta.application.service.IResponseQuestion; // Asegúrate de que esta interfaz exista
import com.abc.encuesta.domain.entities.ResponseQuestion;

@RestController
@RequestMapping("/api/response-questions")
public class ResponseQuestionController {

    @Autowired
    private IResponseQuestion responseQuestionService; // Inyectar el servicio correcto

    @GetMapping
    public ResponseEntity<List<ResponseQuestion>> getAllResponseQuestions() {
        List<ResponseQuestion> responseQuestions = responseQuestionService.findAll();
        return new ResponseEntity<>(responseQuestions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseQuestion> getResponseQuestionById(@PathVariable Long id) {
        Optional<ResponseQuestion> responseQuestion = responseQuestionService.findById(id);
        return responseQuestion.map(ResponseEntity::ok)
                               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ResponseQuestion> createResponseQuestion(@RequestBody ResponseQuestion responseQuestion) {
        ResponseQuestion createdResponseQuestion = responseQuestionService.save(responseQuestion);
        return new ResponseEntity<>(createdResponseQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseQuestion> updateResponseQuestion(@PathVariable Long id, @RequestBody ResponseQuestion responseQuestion) {
        ResponseQuestion updatedResponseQuestion = responseQuestionService.update(id, responseQuestion);
        return updatedResponseQuestion != null ? ResponseEntity.ok(updatedResponseQuestion)
                                               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponseQuestion(@PathVariable Long id) {
        if (responseQuestionService.findById(id).isPresent()) {
            responseQuestionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
