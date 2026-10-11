package com.ticket.code.tickets.comment.model;

import com.ticket.code.tickets.comment.entity.TicketCommentEntity;

public class TicketCommentConverter {

    private TicketCommentConverter() {

    }

    public static TicketCommentModel convertToModel(TicketCommentEntity entity) {

        TicketCommentModel model = new TicketCommentModel();

        model.setCommentID(entity.getCommentID());
        model.setMessage(entity.getMessage());
        model.setTicket(entity.getTicket());
        model.setCreatedDate(entity.getCreatedDate());
        model.setUpdatedDate(entity.getUpdatedDate());

        return model;

    }

    public static TicketCommentEntity convertToEntity(TicketCommentModel model) {

        TicketCommentEntity entity = new TicketCommentEntity();

        entity.setCommentID(model.getCommentID());
        entity.setMessage(model.getMessage());
        entity.setTicket(model.getTicket());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedDate(model.getUpdatedDate());

        return entity;

    }

}
