package com.ticket.code.tickets.area.repository;

import com.ticket.code.tickets.area.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<AreaEntity, Long> {

    AreaEntity findByAreaID(Long areaID);

    AreaEntity findByNameIgnoreCase(String name);

}
