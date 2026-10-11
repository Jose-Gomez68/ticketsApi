package com.ticket.code.tickets.comment.service;

import com.ticket.code.tickets.comment.model.TicketCommentModel;

import java.util.List;
import java.util.Optional;

public interface ITicketCommentService {

    List<TicketCommentModel> allTicketComment();

    TicketCommentModel save (TicketCommentModel ticketComment);

    TicketCommentModel update (TicketCommentModel ticketComment);

    TicketCommentModel deleteTicket(Long ticketCommentID);

    Optional<TicketCommentModel> findByID(Long ticketCommentID);

    List<TicketCommentModel> findByTicketID(Long ticketID);


}
