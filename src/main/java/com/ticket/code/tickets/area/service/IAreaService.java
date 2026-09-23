package com.ticket.code.tickets.area.service;

import com.ticket.code.tickets.area.model.AreaModel;

import java.util.List;
import java.util.Optional;

public interface IAreaService {

    List<AreaModel> allArea();

    AreaModel save (AreaModel area);

    AreaModel update (AreaModel area);

    AreaModel deleteArea(Long areaID);

    Optional<AreaModel> findByID(Long areaID);

    Optional<AreaModel> findByNameIgnoreCase(String name);

}
