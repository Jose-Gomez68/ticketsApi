package com.ticket.code.tickets.area.model;

import com.ticket.code.tickets.area.entity.AreaEntity;

public class AreaConverter {

    private AreaConverter() {

    }

    public static AreaModel convertToModel(AreaEntity entity) {

        AreaModel model = new AreaModel();

        model.setAreaID(entity.getAreaID());
        model.setName(entity.getName());
        model.setDescrip(entity.getDescrip());
        model.setActive(entity.getActive());
        model.setCreatedDate(entity.getCreatedDate());
        model.setCreateBy(entity.getCreateBy());
        model.setUpdatedDate(entity.getUpdatedDate());
        model.setUpdatedBy(entity.getUpdatedBy());

        return model;

    }

    public static AreaEntity convertToEntity(AreaModel model) {

        AreaEntity entity = new AreaEntity();

        entity.setAreaID(model.getAreaID());
        entity.setName(model.getName());
        entity.setDescrip(model.getDescrip());
        entity.setActive(model.getActive());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setCreateBy(model.getCreateBy());
        entity.setUpdatedDate(model.getUpdatedDate());
        entity.setUpdatedBy(model.getUpdatedBy());


        return entity;

    }

}
