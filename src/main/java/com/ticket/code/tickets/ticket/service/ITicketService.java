package com.ticket.code.tickets.ticket.service;

import com.ticket.code.tickets.ticket.model.TicketCreateModel;
import com.ticket.code.tickets.ticket.model.TicketModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ITicketService {

    List<TicketModel> allTicket();

    Page<TicketModel> findByActiveTruePage(Integer page, Integer size);

    TicketModel save (TicketCreateModel ticket);

    TicketModel update (TicketCreateModel ticket);

    TicketModel deleteTicket(Long ticketID);

    Optional<TicketModel> findByID(Long ticketID);

    Optional<TicketModel> findByTitleIgnoreCase(String name);

}
