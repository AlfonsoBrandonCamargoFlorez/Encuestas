package com.abc.encuesta.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.encuesta.application.service.IResponseOptions; // Asegúrate de que esta interfaz exista
import com.abc.encuesta.domain.entities.ResponseOptions;

@RestController
@RequestMapping("/api/response-options")
public class ResponseOptionsController {

    @Autowired
    private IResponseOptions responseOptionsService; // Inyectar el servicio correcto

    @GetMapping
    public ResponseEntity<List<ResponseOptions>> getAllResponseOptions() {
        List<ResponseOptions> responseOptions = responseOptionsService.findAll();
        return new ResponseEntity<>(responseOptions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOptions> getResponseOptionById(@PathVariable Long id) {
        Optional<ResponseOptions> responseOption = responseOptionsService.findById(id);
        return responseOption.map(ResponseEntity::ok)
                             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ResponseOptions> createResponseOption(@RequestBody ResponseOptions responseOption) {
        ResponseOptions createdResponseOption = responseOptionsService.save(responseOption);
        return new ResponseEntity<>(createdResponseOption, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseOptions> updateResponseOption(@PathVariable Long id, @RequestBody ResponseOptions responseOption) {
        ResponseOptions updatedResponseOption = responseOptionsService.update(id, responseOption);
        return updatedResponseOption != null ? ResponseEntity.ok(updatedResponseOption)
                                             : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponseOption(@PathVariable Long id) {
        if (responseOptionsService.findById(id).isPresent()) {
            responseOptionsService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
