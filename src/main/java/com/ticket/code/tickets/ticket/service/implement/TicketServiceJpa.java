package com.ticket.code.tickets.ticket.service.implement;

import com.ticket.code.tickets.ticket.entity.TicketEntity;
import com.ticket.code.tickets.ticket.model.TicketModel;
import com.ticket.code.tickets.ticket.repository.TicketRepository;
import com.ticket.code.tickets.ticket.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class TicketServiceJpa implements ITicketService {

    @Autowired
    private TicketRepository rep;

    @Override
    public List<TicketModel> allTicket() {
        return rep.findAll()
                .stream()
                .map(this::convertToModel)
                .toList();
    }

    @Override
    public List<TicketModel> findByActiveFalse() {
        return rep.findByActiveFalse()
                .stream()
                .map(this::convertToModel)
                .toList();//TODO Modificar para que solo traiga a las que no estan eliminadas osea active=false
    }

    @Override
    public TicketModel save(TicketModel ticket) {
        TicketEntity entity = new TicketEntity();
        entity = convertToEntity(ticket);

        TicketEntity ticketResult = rep.save(entity);

        return convertToModel(ticketResult);
    }

    @Override
    public TicketModel update(TicketModel ticket) {
        TicketEntity ticketResult = rep.save(convertToEntity(ticket));

        return convertToModel(ticketResult);
    }

    @Override
    public TicketModel deleteTicket(Long ticketID) {
        TicketEntity ticket = rep.findByTicektID(ticketID);

        ticket.setActive(true);

        TicketEntity result = rep.save(ticket);

        return convertToModel(result);
    }

    @Override
    public Optional<TicketModel> findByID(Long ticketID) {
        TicketEntity entity = rep.findByTicektID(ticketID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    @Override
    public Optional<TicketModel> findByNameIgnoreCase(String name) {
        TicketEntity entity = rep.findByNameIgnoreCase(name);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    private TicketModel convertToModel(TicketEntity entity) {

        TicketModel model = new TicketModel();

        model.setTicketID(entity.getTicketID());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setActive(entity.getActive());
        model.setCreatedDate(entity.getCreatedDate());
        model.setUpdatedDate(entity.getUpdatedDate());
        model.setKind(entity.getKind());
        model.setUser(entity.getUser());
        model.setAssignedID(entity.getAssignedID());
        model.setProject(entity.getProject());
        model.setAreaID(entity.getAreaID());
        model.setPriorityID(entity.getPriorityID());
        model.setStatusID(entity.getStatusID());
        model.setComments(entity.getComments());


        return model;

    }

    private TicketEntity convertToEntity(TicketModel model) {

        TicketEntity entity = new TicketEntity();

        entity.setTicketID(model.getTicketID());
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setActive(model.getActive());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedDate(model.getUpdatedDate());
        entity.setKind(model.getKind());
        entity.setUser(model.getUser());
        entity.setAssignedID(model.getAssignedID());
        entity.setProject(model.getProject());
        entity.setAreaID(model.getAreaID());
        entity.setPriorityID(model.getPriorityID());
        entity.setStatusID(model.getStatusID());
        entity.setComments(model.getComments());


        return entity;

    }

}
