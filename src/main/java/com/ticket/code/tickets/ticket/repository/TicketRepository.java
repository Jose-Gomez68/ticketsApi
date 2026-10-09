package com.ticket.code.tickets.ticket.repository;

import com.ticket.code.tickets.ticket.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    TicketEntity findByTicektID(Long ticketID);

    List<TicketEntity> findByActiveFalse();

    TicketEntity findByNameIgnoreCase(String name);

    List<TicketEntity> findByAreaID_AreaIDAndActiveFalseOrderByCreatedDateDesc(Long areaID);//ticket no eliminados recientes

    List<TicketEntity> findByAreaID_AreaIDAndActiveTrueOrderByCreatedDateDesc(Long areaID);//ticket eliminados recientes

}
