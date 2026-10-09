package com.ticket.code.tickets.status.service.implement;

import com.ticket.code.tickets.status.entity.StatusEntity;
import com.ticket.code.tickets.status.model.StatusModel;
import com.ticket.code.tickets.status.repository.StatusRepository;
import com.ticket.code.tickets.status.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StatusServiceJpa implements IStatusService {

    @Autowired
    private StatusRepository rep;

    @Override
    public List<StatusModel> allStatus() {
        return rep.findAll()
                .stream()
                .map(this::convertToModel)
                .toList();
    }

    @Override
    public StatusModel save(StatusModel status) {
        StatusEntity entity;

        entity = convertToEntity(status);

        StatusEntity statusResult = rep.save(entity);

        return convertToModel(statusResult);
    }

    @Override
    public StatusModel update(StatusModel status) {
        StatusEntity statusResult = rep.save(convertToEntity(status));

        return convertToModel(statusResult);
    }

    @Override
    public StatusModel deleteStatus(Long statusID) {
        StatusEntity status = rep.findByStatusID(statusID);

        if (status.getName().isEmpty()) {
            return null;
        }


        //StatusModel model = convertToModel(status);

        //rep.delete(status);
        StatusModel model = convertToModel(rep.deleteStatusByStatusID(status.getStatusID()));

        return model;
    }

    @Override
    public Optional<StatusModel> findByID(Long statusID) {
        StatusEntity entity = rep.findByStatusID(statusID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    @Override
    public Optional<StatusModel> findByNameIgnoreCase(String name) {
        StatusEntity entity = rep.findByNameIgnoreCase(name);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    private StatusModel convertToModel(StatusEntity entity) {

        StatusModel model = new StatusModel();

        model.setStatusID(entity.getStatusID());
        model.setName(entity.getName());

        return model;

    }

    private StatusEntity convertToEntity(StatusModel model) {

        StatusEntity entity = new StatusEntity();

        entity.setStatusID(model.getStatusID());
        entity.setName(model.getName());

        return entity;

    }

}
