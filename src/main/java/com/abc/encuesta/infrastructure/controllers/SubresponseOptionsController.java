package com.abc.encuesta.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.encuesta.application.service.ISubresponseOptions; // Asegúrate de que esta interfaz exista
import com.abc.encuesta.domain.entities.SubresponseOptions;

@RestController
@RequestMapping("/api/subresponse-options")
public class SubresponseOptionsController {

    @Autowired
    private ISubresponseOptions subresponseOptionsService; // Inyectar el servicio correcto

    @GetMapping
    public ResponseEntity<List<SubresponseOptions>> getAllSubresponseOptions() {
        List<SubresponseOptions> subresponseOptionsList = subresponseOptionsService.findAll();
        return new ResponseEntity<>(subresponseOptionsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubresponseOptions> getSubresponseOptionById(@PathVariable Long id) {
        Optional<SubresponseOptions> subresponseOption = subresponseOptionsService.findById(id);
        return subresponseOption.map(ResponseEntity::ok)
                                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SubresponseOptions> createSubresponseOption(@RequestBody SubresponseOptions subresponseOption) {
        SubresponseOptions createdSubresponseOption = subresponseOptionsService.save(subresponseOption);
        return new ResponseEntity<>(createdSubresponseOption, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubresponseOptions> updateSubresponseOption(@PathVariable Long id, @RequestBody SubresponseOptions subresponseOption) {
        SubresponseOptions updatedSubresponseOption = subresponseOptionsService.update(id, subresponseOption);
        return updatedSubresponseOption != null ? ResponseEntity.ok(updatedSubresponseOption)
                                                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubresponseOption(@PathVariable Long id) {
        if (subresponseOptionsService.findById(id).isPresent()) {
            subresponseOptionsService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
