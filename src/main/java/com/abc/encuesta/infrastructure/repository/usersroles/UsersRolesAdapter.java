package com.abc.encuesta.infrastructure.repository.usersroles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.IUsersRoles; // Asegúrate de tener esta interfaz
import com.abc.encuesta.domain.entities.UsersRoles;

@Service
public class UsersRolesAdapter implements IUsersRoles {

    @Autowired
    private UsersRolesRepository usersRolesRepository;

    @Override
    public UsersRoles save(UsersRoles usersRoles) {
        return usersRolesRepository.save(usersRoles);
    }

    @Override
    public void deleteById(Long id) {
        usersRolesRepository.deleteById(id);
    }

    @Override
    public List<UsersRoles> findAll() {
        return usersRolesRepository.findAll();
    }

    @Override
    public Optional<UsersRoles> findById(Long id) {
        return usersRolesRepository.findById(id);
    }

    @Override
    public UsersRoles update(Long id, UsersRoles usersRoles) {
        return usersRolesRepository.findById(id)
            .map(existingUsersRoles -> {
                // Actualizar los campos relevantes
                
                existingUsersRoles.setUsers(usersRoles.getUsers());
                
                // Asegúrate de actualizar otros campos si es necesario
                return usersRolesRepository.save(existingUsersRoles);
            })
            .orElse(null);
    }
}
