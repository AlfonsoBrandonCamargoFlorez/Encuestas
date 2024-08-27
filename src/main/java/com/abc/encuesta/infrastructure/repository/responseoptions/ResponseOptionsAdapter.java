package com.abc.encuesta.infrastructure.repository.responseoptions;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.IResponseOptions;
import com.abc.encuesta.domain.entities.Audit;
import com.abc.encuesta.domain.entities.ResponseOptions;
import java.time.LocalDateTime;

@Service
public class ResponseOptionsAdapter implements IResponseOptions {

    @Autowired
    private ResponseOptionsRepository responseOptionsRepository;

    @Override
    public Optional<ResponseOptions> findById(Long id) {
        return responseOptionsRepository.findById(id);
    }

    @Override
    public ResponseOptions save(ResponseOptions responseOptions) {
        return responseOptionsRepository.save(responseOptions);
    }

    @Override
    public void deleteById(Long id) {
        responseOptionsRepository.deleteById(id);
    }

    @Override
    public List<ResponseOptions> findAll() {
        return responseOptionsRepository.findAll();
    }

    @Override
    public ResponseOptions update(Long id, ResponseOptions responseOptions) {
        return responseOptionsRepository.findById(id)
                .map(existingResponseOption -> {
                    // Actualizar los campos
                    existingResponseOption.setOption_text(responseOptions.getOption_text());
                    existingResponseOption.setTypecomponenthtml(responseOptions.getTypecomponenthtml());
                    existingResponseOption.setComment_response(responseOptions.getComment_response());

                    // Actualizar el campo updatedAt en el audit
                    Audit audit = existingResponseOption.getAudit();
                    audit.setUpdatedAt(LocalDateTime.now());
                    existingResponseOption.setAudit(audit);

                    return responseOptionsRepository.save(existingResponseOption);
                })
                .orElse(null);
    }
}
