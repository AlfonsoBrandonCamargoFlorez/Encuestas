package com.abc.encuesta.infrastructure.repository.roles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.IRoles; // Asegúrate de tener esta interfaz

import com.abc.encuesta.domain.entities.Roles;

@Service
public class RolesAdapter implements IRoles {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Roles save(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public void deleteById(Long id) {
        rolesRepository.deleteById(id);
    }

    @Override
    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }

    
    @Override
    public Optional<Roles> findByid(Long id) {
        return rolesRepository.findById(id);
    }

    @Override
    public Roles updade(Long id, Roles roles) {
        return rolesRepository.findById(id)
        .map(existingRoles -> {
            // Actualizar los campos relevantes
            existingRoles.setName(roles.getName());
            return rolesRepository.save(existingRoles);
        })
        .orElse(null);}

}
