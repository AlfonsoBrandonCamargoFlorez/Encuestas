package com.abc.encuesta.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.encuesta.application.service.IRoles; // Asegúrate de que esta interfaz exista
import com.abc.encuesta.domain.entities.Roles;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    @Autowired
    private IRoles rolesService; // Inyectar el servicio correcto

    @GetMapping
    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> roles = rolesService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> getRoleById(@PathVariable Long id) {
        Optional<Roles> role = rolesService.findById(id);
        return role.map(ResponseEntity::ok)
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Roles> createRole(@RequestBody Roles role) {
        Roles createdRole = rolesService.save(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Roles> updateRole(@PathVariable Long id, @RequestBody Roles role) {
        Roles updatedRole = rolesService.updade(id, role);
        return updatedRole != null ? ResponseEntity.ok(updatedRole)
                                   : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (rolesService.findById(id).isPresent()) {
            rolesService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
