package com.ticket.code.tickets.status.repository;

import com.ticket.code.tickets.status.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {

    StatusEntity findByStatusID(Long statusID);

    StatusEntity findByNameIgnoreCase(String name);

    StatusEntity deleteStatusByStatusID(Long statusID);

}
