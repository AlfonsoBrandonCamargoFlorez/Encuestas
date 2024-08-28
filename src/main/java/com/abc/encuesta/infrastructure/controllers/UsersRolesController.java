package com.abc.encuesta.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.encuesta.application.service.IUsersRoles; // Asegúrate de que esta interfaz exista
import com.abc.encuesta.domain.entities.UsersRoles;

@RestController
@RequestMapping("/api/users-roles")
public class UsersRolesController {

    @Autowired
    private IUsersRoles usersRolesService; // Inyectar el servicio correcto

    @GetMapping
    public ResponseEntity<List<UsersRoles>> getAllUsersRoles() {
        List<UsersRoles> usersRolesList = usersRolesService.findAll();
        return new ResponseEntity<>(usersRolesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersRoles> getUserRoleById(@PathVariable Long id) {
        Optional<UsersRoles> userRole = usersRolesService.findById(id);
        return userRole.map(ResponseEntity::ok)
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UsersRoles> createUserRole(@RequestBody UsersRoles userRole) {
        UsersRoles createdUserRole = usersRolesService.save(userRole);
        return new ResponseEntity<>(createdUserRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersRoles> updateUserRole(@PathVariable Long id, @RequestBody UsersRoles userRole) {
        UsersRoles updatedUserRole = usersRolesService.update(id, userRole);
        return updatedUserRole != null ? ResponseEntity.ok(updatedUserRole)
                                       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        if (usersRolesService.findById(id).isPresent()) {
            usersRolesService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
