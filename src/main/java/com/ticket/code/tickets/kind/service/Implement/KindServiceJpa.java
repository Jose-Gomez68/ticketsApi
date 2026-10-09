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
        KindEntity entity = new KindEntity();

        entity = convertToEntity(kind);

        KindEntity savedEntity = rep.save(entity);

        return convertToModel(savedEntity);
    }

    @Override
    public KindModel update(KindModel kind) {

        KindEntity kindResult = rep.save(convertToEntity(kind));

        return convertToModel(kindResult);
    }

    @Override
    public KindModel deleteKind(Long kindID) {
        KindEntity kind = rep.findByKindID(kindID);

        kind.setActive(true);

        KindEntity result = rep.save(kind);

        return convertToModel(result);
    }

    @Override
    public Optional<KindModel> findByID(Long kindID) {
        KindEntity entity = rep.findByKindID(kindID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    @Override
    public Optional<KindModel> findByNameIgnoreCase(String name) {

        KindEntity entity = rep.findByNameIgnoreCase(name);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));

    }

    private KindModel convertToModel(KindEntity entity) {

        KindModel model = new KindModel();

        model.setKindID(entity.getKindID());
        model.setName(entity.getName());
        //model.setTickets(entity.getTickets());
        model.setActive(entity.getActive());
        model.setCreatedBy(entity.getCreateBy());
        model.setCreatedDate(entity.getCreateDate());
        model.setUpdatedBy(entity.getUpdatedBy());
        model.setUpdatedDate(entity.getUpdatedDate());
        model.setDeletedBy(entity.getDeletedBy());
        model.setDeletedDate(entity.getDeletedDate());

        return model;
    }

    private KindEntity convertToEntity(KindModel model) {

        KindEntity entity = new KindEntity();

        entity.setKindID(model.getKindID());
        entity.setName(model.getName());
        //entity.setTickets(model.getTickets());
        entity.setActive(model.getActive());
        entity.setCreateBy(model.getCreatedBy());
        entity.setCreateDate(model.getCreatedDate());
        entity.setUpdatedBy(model.getUpdatedBy());
        entity.setUpdatedDate(model.getUpdatedDate());
        entity.setDeletedBy(model.getDeletedBy());
        entity.setDeletedDate(model.getDeletedDate());

        return entity;
    }

}
