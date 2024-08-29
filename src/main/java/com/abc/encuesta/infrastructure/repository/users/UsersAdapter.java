package com.abc.encuesta.infrastructure.repository.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.encuesta.application.service.IUsers; // Asegúrate de tener esta interfaz
import com.abc.encuesta.domain.entities.Users;

@Service
public class UsersAdapter implements IUsers {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users save(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Users update(Long id, Users users) {
        return usersRepository.findById(id)
            .map(existingUsers -> {
                // Actualizar los campos relevantes
                existingUsers.setUsername(users.getUsername());                
                existingUsers.setPassword(users.getPassword());
                existingUsers.setEnabled(users.getEnabled());
                // Asegúrate de actualizar otros campos si es necesario
                return usersRepository.save(existingUsers);
            })
            .orElse(null);
    }
}
