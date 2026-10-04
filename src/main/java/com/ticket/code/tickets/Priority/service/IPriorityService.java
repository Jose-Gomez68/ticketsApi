package com.ticket.code.tickets.Priority.service;

import com.ticket.code.tickets.Priority.model.PriorityModel;

import java.util.List;
import java.util.Optional;

public interface IPriorityService {

    List<PriorityModel> allPriority();

    PriorityModel save (PriorityModel area);

    PriorityModel update (PriorityModel area);

    PriorityModel deletePriority(Long areaID);

    Optional<PriorityModel> findByID(Long areaID);

    Optional<PriorityModel> findByNameIgnoreCase(String name);

}
