package com.ticket.code.tickets.usuarios.service;

import com.ticket.code.tickets.usuarios.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserModel> allUsers();

    UserModel save (UserModel user);

    UserModel update (UserModel user);

    void deleteUser(Long userID);

    Optional<UserModel> findByID(Long userID);

    Optional<UserModel> findByEmailIgnoreCase(String email);

}
