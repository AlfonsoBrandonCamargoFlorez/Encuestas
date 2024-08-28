package com.abc.encuesta.infrastructure.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.encuesta.domain.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
