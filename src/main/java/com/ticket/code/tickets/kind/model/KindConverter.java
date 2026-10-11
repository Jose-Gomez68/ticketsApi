package com.ticket.code.tickets.kind.model;

import com.ticket.code.tickets.kind.entity.KindEntity;

public class KindConverter {

    private KindConverter() {

    }

    public static KindModel convertToModel(KindEntity entity) {

        KindModel model = new KindModel();

        model.setKindID(entity.getKindID());
        model.setName(entity.getName());
        //model.setTickets(entity.getTickets());
        model.setActive(entity.getActive());
        model.setCreatedBy(entity.getCreateBy());
        model.setCreatedDate(entity.getCreateDate());
        model.setUpdatedBy(entity.getUpdatedBy());
        model.setUpdatedDate(entity.getUpdatedDate());
        model.setDeletedBy(entity.getDeletedBy());
        model.setDeletedDate(entity.getDeletedDate());

        return model;
    }

    public static KindEntity convertToEntity(KindModel model) {

        KindEntity entity = new KindEntity();

        entity.setKindID(model.getKindID());
        entity.setName(model.getName());
        //entity.setTickets(model.getTickets());
        entity.setActive(model.getActive());
        entity.setCreateBy(model.getCreatedBy());
        entity.setCreateDate(model.getCreatedDate());
        entity.setUpdatedBy(model.getUpdatedBy());
        entity.setUpdatedDate(model.getUpdatedDate());
        entity.setDeletedBy(model.getDeletedBy());
        entity.setDeletedDate(model.getDeletedDate());

        return entity;
    }

}
