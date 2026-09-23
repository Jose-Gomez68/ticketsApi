package com.ticket.code.tickets.usuarios.service.implement;

import com.ticket.code.tickets.usuarios.entity.UserEntity;
import com.ticket.code.tickets.usuarios.model.UserModel;
import com.ticket.code.tickets.usuarios.repository.UserRepository;
import com.ticket.code.tickets.usuarios.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceJpa implements IUserService {

    @Autowired
    private UserRepository rep;

    @Override
    public List<UserModel> allUsers() {
        return rep.findAll()
                .stream()
                .map(this::convertToModel)
                .toList();
    }

    @Override
    public UserModel save(UserModel user) {
        UserEntity entity = convertToEntity(user);

        UserEntity savedEntity = rep.save(entity);

        return convertToModel(savedEntity);
    }

    @Override
    public UserModel update(UserModel user) {

        UserEntity entity = rep.save(convertToEntity(user));

        return convertToModel(entity);
    }

    @Override
    public void deleteUser(Long userID) {
        rep.deleteById(userID);
    }

    @Override
    public Optional<UserModel> findByID(Long userID) {
        UserEntity entity = rep.findByUserID(userID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    @Override
    public Optional<UserModel> findByEmailIgnoreCase(String email) {

        UserEntity entity = rep.findByEmailIgnoreCase(email);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));

    }

    private UserModel convertToModel(UserEntity entity) {

        UserModel model = new UserModel();

        model.setUserID(entity.getUserID());
        model.setUserName(entity.getUserName());
        model.setName(entity.getName());
        model.setApPaterno(entity.getApPaterno());
        model.setApMaterno(entity.getApMaterno());
        model.setEmail(entity.getEmail());

        // No regresar password
        // model.setPassword(entity.getPassword());

        model.setActive(entity.getActive());
        model.setCreateBy(entity.getCreateBy());
        model.setCreateDate(entity.getCreateDate());
        model.setUpdatedBy(entity.getUpdatedBy());
        model.setUpdatedDate(entity.getUpdatedDate());
        model.setDeletedBy(entity.getDeletedBy());
        model.setDeletedDate(entity.getDeletedDate());

        return model;
    }

    private UserEntity convertToEntity(UserModel model) {

        UserEntity entity = new UserEntity();

        entity.setUserID(model.getUserID());
        entity.setUserName(model.getUserName());
        entity.setName(model.getName());
        entity.setApPaterno(model.getApPaterno());
        entity.setApMaterno(model.getApMaterno());
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        entity.setActive(model.getActive());
        entity.setCreateBy(model.getCreateBy());
        entity.setCreateDate(model.getCreateDate());
        entity.setUpdatedBy(model.getUpdatedBy());
        entity.setUpdatedDate(model.getUpdatedDate());
        entity.setDeletedBy(model.getDeletedBy());
        entity.setDeletedDate(model.getDeletedDate());

        return entity;
    }

}
