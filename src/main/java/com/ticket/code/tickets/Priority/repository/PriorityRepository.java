package com.ticket.code.tickets.Priority.repository;

import com.ticket.code.tickets.Priority.entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {

    PriorityEntity findByPriorityID(Long priorityID);

    PriorityEntity findByNameIgnoreCase(String name);

}
