package com.ticket.code.tickets.Priority.service.implement;

import com.ticket.code.tickets.Priority.entity.PriorityEntity;
import com.ticket.code.tickets.Priority.model.PriorityModel;
import com.ticket.code.tickets.Priority.repository.PriorityRepository;
import com.ticket.code.tickets.Priority.service.IPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class PriorityServiceJpa implements IPriorityService {

    @Autowired
    private PriorityRepository rep;

    @Override
    public List<PriorityModel> allPriority() {
        return rep.findAll()
                .stream()
                .map(this::convertToModel)
                .toList();
    }

    @Override
    public PriorityModel save(PriorityModel area) {
        PriorityEntity entity = new PriorityEntity();

        entity.setName(area.getName());

        PriorityEntity priorityResult = rep.save(entity);

        return convertToModel(priorityResult);
    }

    @Override
    public PriorityModel update(PriorityModel priority) {
        PriorityEntity priorityResult = rep.save(convertToEntity(priority));

        return convertToModel(priorityResult);
    }

    @Override
    public PriorityModel deletePriority(Long priorityID) {
        PriorityEntity priority = rep.findByPriorityID(priorityID);

        priority.setActive(true);

        PriorityEntity result = rep.save(priority);

        return convertToModel(result);
    }

    @Override
    public Optional<PriorityModel> findByID(Long prioID) {
        PriorityEntity entity = rep.findByPriorityID(prioID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    @Override
    public Optional<PriorityModel> findByNameIgnoreCase(String name) {
        PriorityEntity entity = rep.findByNameIgnoreCase(name);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    private PriorityModel convertToModel(PriorityEntity entity) {

        PriorityModel model = new PriorityModel();

        model.setPriorityID(entity.getPriorityID());
        model.setName(entity.getName());
        model.setActive(entity.getActive());
        model.setCreatedBy(entity.getCreatedBy());
        model.setCreatedDate(entity.getCreatedDate());
        model.setUpdatedBy(entity.getCreatedBy());
        model.setUpdatedDate(entity.getUpdatedDate());

        return model;

    }

    private PriorityEntity convertToEntity(PriorityModel model) {

        PriorityEntity entity = new PriorityEntity();

        entity.setPriorityID(model.getPriorityID());
        entity.setName(model.getName());
        entity.setActive(model.getActive());
        entity.setCreatedBy(model.getCreatedBy());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedBy(model.getCreatedBy());
        entity.setUpdatedDate(model.getUpdatedDate());

        return entity;

    }

}
