package com.abc.encuesta.infrastructure.repository.chapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.IChapter;
import com.abc.encuesta.domain.entities.Audit;
import com.abc.encuesta.domain.entities.Chapter;

import java.time.LocalDateTime;

@Service
public class ChapterAdapter implements IChapter {
    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public Optional<Chapter> findById(Long id) {
        return chapterRepository.findById(id);
    }

    @Override
    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public void deleteById(Long id) {
        chapterRepository.deleteById(id);
    }

    @Override
    public List<Chapter> findAll() {
        return chapterRepository.findAll();
    }

    @Override
    public Chapter update(Long id, Chapter chapter) {
        return chapterRepository.findById(id)
                .map(existingChapter -> {
                    // Mantener el createdAt existente
                    existingChapter.setChapter_title(chapter.getChapter_title());
                    existingChapter.setSurveys(chapter.getSurveys());
                    existingChapter.setChapter_number(chapter.getChapter_number());

                    // Actualizar el campo updatedAt en el audit
                    Audit audit = existingChapter.getAudit();
                    audit.setUpdatedAt(LocalDateTime.now());
                    existingChapter.setAudit(audit);

                    return chapterRepository.save(existingChapter);
                })
                .orElse(null);

    }

}
