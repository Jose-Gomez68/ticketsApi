package com.ticket.code.tickets.kind.service.Implement;

import com.ticket.code.tickets.kind.entity.KindEntity;
import com.ticket.code.tickets.kind.model.KindModel;
import com.ticket.code.tickets.kind.repository.KindRepository;
import com.ticket.code.tickets.kind.service.IKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class KindServiceJpa implements IKindService {

    @Autowired
    private KindRepository rep;

    @Override
    public List<KindModel> allKind() {
        return List.of();
    }

    @Override
    public KindModel save(KindModel kind) {
        return null;
    }

    @Override
    public KindModel update(KindModel area) {
        return null;
    }

    @Override
    public KindModel deleteKind(Long areaID) {
        return null;
    }

    @Override
    public Optional<KindModel> findByID(Long areaID) {
        return Optional.empty();
    }

    @Override
    public Optional<KindModel> findByNameIgnoreCase(String name) {
        return Optional.empty();
    }

    private KindModel convertToModel(KindEntity entity) {

        KindModel model = new KindModel();

        model.setKindID(entity.getKindID());
        model.setName(entity.getName());

        return model;
    }

    private KindEntity convertToEntity(KindModel model) {

        KindEntity entity = new KindEntity();

        entity.setKindID(model.getKindID());
        entity.setName(model.getName());

        return entity;
    }

}
