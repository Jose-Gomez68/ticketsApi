package com.ticket.code.tickets.ticket.model;

import com.ticket.code.tickets.Priority.model.PriorityConverter;
import com.ticket.code.tickets.area.model.AreaConverter;
import com.ticket.code.tickets.comment.model.TicketCommentConverter;
import com.ticket.code.tickets.kind.model.KindConverter;
import com.ticket.code.tickets.project.model.ProjectConverter;
import com.ticket.code.tickets.status.model.StatusConverter;
import com.ticket.code.tickets.ticket.entity.TicketEntity;
import com.ticket.code.tickets.usuarios.model.UserConverter;

public class TicketConverter {

    private TicketConverter() {

    }

    public static TicketModel convertToModel(TicketEntity entity) {

        if (entity == null) {
            return null;
        }

        TicketModel model = new TicketModel();

        model.setTicketID(entity.getTicketID());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setActive(entity.getActive());
        model.setCreatedDate(entity.getCreatedDate());
        model.setUpdatedDate(entity.getUpdatedDate());

        model.setKind(KindConverter.convertToModel(entity.getKind()));
        model.setUser(UserConverter.convertToModel(entity.getUser()));
        model.setAssignedID(UserConverter.convertToModel(entity.getAssignedID()));
        model.setProject(ProjectConverter.convertToModel(entity.getProject()));
        model.setAreaID(AreaConverter.convertToModel(entity.getAreaID()));
        model.setPriorityID(PriorityConverter.convertToModel(entity.getPriorityID()));
        model.setStatusID(StatusConverter.convertToModel(entity.getStatusID()));

        model.setComments(
                entity.getComments().stream()
                        .map(TicketCommentConverter::convertToModel) // ajusta si tu converter de comment se llama distinto
                        .toList()
        );

        return model;
    }

    public static TicketEntity convertToEntity(TicketModel model) {

        if (model == null) {
            return null;
        }

        TicketEntity entity = new TicketEntity();

        entity.setTicketID(model.getTicketID());
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setActive(model.getActive());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedDate(model.getUpdatedDate());

        entity.setKind(KindConverter.convertToEntity(model.getKind()));
        entity.setUser(UserConverter.convertToEntity(model.getUser()));
        entity.setAssignedID(UserConverter.convertToEntity(model.getAssignedID()));
        entity.setProject(ProjectConverter.convertToEntity(model.getProject()));
        entity.setAreaID(AreaConverter.convertToEntity(model.getAreaID()));
        entity.setPriorityID(PriorityConverter.convertToEntity(model.getPriorityID()));
        entity.setStatusID(StatusConverter.convertToEntity(model.getStatusID()));

        entity.setComments(
                model.getComments().stream()
                        .map(TicketCommentConverter::convertToEntity)
                        .toList()
        );

        return entity;
    }


}
