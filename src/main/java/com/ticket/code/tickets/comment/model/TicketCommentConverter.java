package com.ticket.code.tickets.comment.model;

import com.ticket.code.tickets.comment.entity.TicketCommentEntity;
import com.ticket.code.tickets.ticket.model.TicketConverter;

public class TicketCommentConverter {

    private TicketCommentConverter() {

    }

    public static TicketCommentModel convertToModel(TicketCommentEntity entity) {

        TicketCommentModel model = new TicketCommentModel();

        model.setCommentID(entity.getCommentID());
        model.setMessage(entity.getMessage());
        model.setTicket(TicketConverter.convertToModel(entity.getTicket()).getTicketID());
        model.setCreatedDate(entity.getCreatedDate());
        model.setUpdatedDate(entity.getUpdatedDate());

        return model;

    }

    public static TicketCommentEntity convertToEntity(TicketComment2Model model) {

        TicketCommentEntity entity = new TicketCommentEntity();

        entity.setCommentID(model.getCommentID());
        entity.setMessage(model.getMessage());
        entity.setTicket(TicketConverter.convertToEntity(model.getTicket()));
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedDate(model.getUpdatedDate());

        return entity;

    }

    public static TicketCommentEntity convertToEntity(
            TicketCommentModel model
    ) {
        if (model == null) {
            return null;
        }

        TicketCommentEntity entity = new TicketCommentEntity();

        entity.setCommentID(model.getCommentID());
        entity.setMessage(model.getMessage());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedDate(model.getUpdatedDate());

        return entity;
    }

}
