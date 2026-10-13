package com.ticket.code.tickets.comment.repository;

import com.ticket.code.tickets.comment.entity.TicketCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketCommentRepository extends JpaRepository<TicketCommentEntity, Long> {

    TicketCommentEntity findByCommentID(Long ticketCommentID);

    List<TicketCommentEntity> findByTicket_TicketID(Long ticketID);


}
