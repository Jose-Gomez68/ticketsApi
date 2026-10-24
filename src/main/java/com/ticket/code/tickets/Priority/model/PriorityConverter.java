package com.ticket.code.tickets.Priority.model;

import com.ticket.code.tickets.Priority.entity.PriorityEntity;

public class PriorityConverter {

    private PriorityConverter() {

    }

    public static PriorityModel convertToModel(PriorityEntity entity) {

        PriorityModel model = new PriorityModel();

        model.setPriorityID(entity.getPriorityID());
        model.setName(entity.getName());
        model.setBackgroundColor(entity.getBackgroundColor());
        model.setDotColor(entity.getDotColor());
        model.setActive(entity.getActive());
        model.setCreatedBy(entity.getCreatedBy());
        model.setCreatedDate(entity.getCreatedDate());
        model.setUpdatedBy(entity.getCreatedBy());
        model.setUpdatedDate(entity.getUpdatedDate());

        return model;

    }

    public static PriorityEntity convertToEntity(PriorityModel model) {

        PriorityEntity entity = new PriorityEntity();

        entity.setPriorityID(model.getPriorityID());
        entity.setName(model.getName());
        entity.setBackgroundColor(model.getBackgroundColor());
        entity.setDotColor(model.getDotColor());
        entity.setActive(model.getActive());
        entity.setCreatedBy(model.getCreatedBy());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedBy(model.getCreatedBy());
        entity.setUpdatedDate(model.getUpdatedDate());

        return entity;

    }

}
