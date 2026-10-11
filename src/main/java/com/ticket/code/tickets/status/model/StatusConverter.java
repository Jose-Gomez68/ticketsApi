package com.ticket.code.tickets.status.model;

import com.ticket.code.tickets.status.entity.StatusEntity;

public class StatusConverter {

    private StatusConverter() {
    }

    public static StatusModel convertToModel(StatusEntity entity) {

        StatusModel model = new StatusModel();

        model.setStatusID(entity.getStatusID());
        model.setName(entity.getName());

        return model;

    }

    public static StatusEntity convertToEntity(StatusModel model) {

        StatusEntity entity = new StatusEntity();

        entity.setStatusID(model.getStatusID());
        entity.setName(model.getName());

        return entity;

    }

}
