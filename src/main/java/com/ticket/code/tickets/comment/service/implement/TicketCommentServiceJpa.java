package com.ticket.code.tickets.comment.service.implement;

import com.ticket.code.tickets.comment.entity.TicketCommentEntity;
import com.ticket.code.tickets.comment.model.TicketCommentConverter;
import com.ticket.code.tickets.comment.model.TicketCommentModel;
import com.ticket.code.tickets.comment.repository.TicketCommentRepository;
import com.ticket.code.tickets.comment.service.ITicketCommentService;
import com.ticket.code.tickets.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ticket.code.tickets.comment.model.TicketCommentConverter.convertToModel;

@Service
@Primary
public class TicketCommentServiceJpa implements ITicketCommentService {

    @Autowired
    private TicketCommentRepository rep;

    @Autowired
    private TicketRepository ticket;

    @Override
    public List<TicketCommentModel> allTicketComment() {
        return rep.findAll()
                .stream()
                .map(TicketCommentConverter::convertToModel)
                .toList();
    }

    @Override
    public TicketCommentModel save(TicketCommentModel ticketComment) {
        TicketCommentEntity entity = new TicketCommentEntity();

        entity = convertToEntity2(ticketComment);

        TicketCommentEntity commentResult = rep.save(entity);

        return convertToModel(commentResult);
    }

    @Override
    public TicketCommentModel update(TicketCommentModel ticketComment) {
        TicketCommentEntity commentResult = rep.save(convertToEntity2(ticketComment));

        return convertToModel(commentResult);
    }

    @Override
    public TicketCommentModel deleteTicket(Long ticketCommentID) {
        TicketCommentEntity comment = rep.findByCommentID(ticketCommentID);

        if (comment != null)
            rep.deleteById(ticketCommentID);

        return convertToModel(comment);

    }

    @Override
    public Optional<TicketCommentModel> findByID(Long ticketCommentID) {
        TicketCommentEntity entity = rep.findByCommentID(ticketCommentID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    @Override
    public List<TicketCommentModel> findByTicketID(Long ticketID) {
        return rep.findByTicket_TicketID(ticketID)
                .stream()
                .map(TicketCommentConverter::convertToModel)
                .toList();
    }

    private TicketCommentEntity convertToEntity2(TicketCommentModel model) {

        TicketCommentEntity entity = new TicketCommentEntity();

        entity.setCommentID(model.getCommentID());
        entity.setMessage(model.getMessage());
        entity.setTicket( ticket.findById(model.getTicket())
                .orElseThrow(() ->
                        new RuntimeException("Ticket no encontrado")));
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedDate(model.getUpdatedDate());

        return entity;

    }


    /*private TicketCommentModel convertToModel(TicketCommentEntity entity) {

        TicketCommentModel model = new TicketCommentModel();

        model.setCommentID(entity.getCommentID());
        model.setMessage(entity.getMessage());
        model.setTicket(entity.getTicket());
        model.setCreatedDate(entity.getCreatedDate());
        model.setUpdatedDate(entity.getUpdatedDate());

        return model;

    }

    private TicketCommentEntity convertToEntity(TicketCommentModel model) {

        TicketCommentEntity entity = new TicketCommentEntity();

        entity.setCommentID(model.getCommentID());
        entity.setMessage(model.getMessage());
        entity.setTicket(model.getTicket());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedDate(model.getUpdatedDate());

        return entity;

    }*/

}
