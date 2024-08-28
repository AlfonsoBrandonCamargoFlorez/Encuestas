package com.abc.encuesta.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.encuesta.application.service.IChapter; // Importar la interfaz correcta
import com.abc.encuesta.domain.entities.Chapter;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    @Autowired
    private IChapter chapterService; // Inyectar el servicio correcto

    @GetMapping
    public ResponseEntity<List<Chapter>> getAllChapters() {
        List<Chapter> chapters = chapterService.findAll();
        return new ResponseEntity<>(chapters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable Long id) {
        Optional<Chapter> chapter = chapterService.findById(id);
        return chapter.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Chapter> createChapter(@RequestBody Chapter chapter) {
        Chapter createdChapter = chapterService.save(chapter);
        return new ResponseEntity<>(createdChapter, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chapter> updateChapter(@PathVariable Long id, @RequestBody Chapter chapter) {
        Chapter updatedChapter = chapterService.update(id, chapter);
        return updatedChapter != null ? ResponseEntity.ok(updatedChapter)
                                       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        if (chapterService.findById(id).isPresent()) {
            chapterService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
