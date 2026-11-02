package com.ticket.code.tickets.usuarios.repository;

import com.ticket.code.tickets.usuarios.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserID(Long userID);

    Optional<UserEntity> findByEmailIgnoreCase(String email);

}
