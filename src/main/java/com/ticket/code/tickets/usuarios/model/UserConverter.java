package com.ticket.code.tickets.usuarios.model;

import com.ticket.code.tickets.usuarios.entity.UserEntity;

public class UserConverter {

    private UserConverter() {

    }

    public static UserModel convertToModel(UserEntity entity) {

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

    public static UserEntity convertToEntity(UserModel model) {

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
