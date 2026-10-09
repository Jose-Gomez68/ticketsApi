package com.ticket.code.tickets.ticket.service;

import com.ticket.code.tickets.ticket.model.TicketModel;

import java.util.List;
import java.util.Optional;

public interface ITicketService {

    List<TicketModel> allTicket();

    List<TicketModel> findByActiveFalse();

    TicketModel save (TicketModel ticket);

    TicketModel update (TicketModel ticket);

    TicketModel deleteTicket(Long ticketID);

    Optional<TicketModel> findByID(Long ticketID);

    Optional<TicketModel> findByNameIgnoreCase(String name);

}
