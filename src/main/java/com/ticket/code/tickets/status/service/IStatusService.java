package com.ticket.code.tickets.status.service;

import com.ticket.code.tickets.status.model.StatusModel;

import java.util.List;
import java.util.Optional;

public interface IStatusService {

    List<StatusModel> allStatus();

    StatusModel save (StatusModel status);

    StatusModel update (StatusModel status);

    StatusModel deleteStatus(Long statusID);

    Optional<StatusModel> findByID(Long statusID);

    Optional<StatusModel> findByNameIgnoreCase(String name);

}
