package com.ticket.code.tickets.kind.service;

import com.ticket.code.tickets.kind.model.KindModel;

import java.util.List;
import java.util.Optional;

public interface IKindService {

    List<KindModel> allKind();

    KindModel save (KindModel kind);

    KindModel update (KindModel area);

    KindModel deleteKind(Long areaID);

    Optional<KindModel> findByID(Long areaID);

    Optional<KindModel> findByNameIgnoreCase(String name);

}
