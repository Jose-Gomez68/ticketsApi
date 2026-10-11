package com.ticket.code.tickets.comment.repository;

import com.ticket.code.tickets.comment.entity.TicketCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCommentRepository extends JpaRepository<TicketCommentEntity, Long> {

    TicketCommentEntity findByTicketCommentID(Long ticketCommentID);

    TicketCommentEntity findByTicketID(Long ticketID);


}
