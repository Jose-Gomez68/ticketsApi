package com.ticket.code.tickets.area.model;

import com.ticket.code.tickets.area.entity.AreaEntity;

public class AreaConverter {

    private AreaConverter() {

    }

    public static AreaModel convertToModel(AreaEntity entity) {

        AreaModel model = new AreaModel();

        model.setAreaID(entity.getAreaID());
        model.setName(entity.getName());
        model.setActive(entity.getActive());

        return model;

    }

    public static AreaEntity convertToEntity(AreaModel model) {

        AreaEntity entity = new AreaEntity();

        entity.setAreaID(model.getAreaID());
        entity.setName(model.getName());
        entity.setActive(model.getActive());

        return entity;

    }

}
