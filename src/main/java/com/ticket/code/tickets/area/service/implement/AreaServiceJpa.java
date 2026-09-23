package com.ticket.code.tickets.area.service.implement;

import com.ticket.code.tickets.area.entity.AreaEntity;
import com.ticket.code.tickets.area.model.AreaModel;
import com.ticket.code.tickets.area.repository.AreaRepository;
import com.ticket.code.tickets.area.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class AreaServiceJpa implements IAreaService {

    @Autowired
    private AreaRepository rep;

    @Override
    public List<AreaModel> allArea() {
        return rep.findAll()
                .stream()
                .map(this::convertToModel)
                .toList();
    }

    @Override
    public AreaModel save(AreaModel area) {

        AreaEntity entity = new AreaEntity();

        entity.setName(area.getName());

        AreaEntity areaResult = rep.save(entity);

        return convertToModel(areaResult);
    }

    @Override
    public AreaModel update(AreaModel area) {

        AreaEntity areaResult = rep.save(convertToEntity(area));

        return convertToModel(areaResult);
    }

    @Override
    public AreaModel deleteArea(Long areaID) {
        AreaEntity area = rep.findByAreaID(areaID);

        area.setActive(true);

        AreaEntity result = rep.save(area);

        return convertToModel(result);
    }

    @Override
    public Optional<AreaModel> findByID(Long areaID) {

        AreaEntity entity = rep.findByAreaID(areaID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    @Override
    public Optional<AreaModel> findByNameIgnoreCase(String name) {

        AreaEntity entity = rep.findByNameIgnoreCase(name);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    private AreaModel convertToModel(AreaEntity entity) {

        AreaModel model = new AreaModel();

        model.setAreaID(entity.getAreaID());
        model.setName(entity.getName());
        model.setActive(entity.getActive());

        return model;

    }

    private AreaEntity convertToEntity(AreaModel model) {

        AreaEntity entity = new AreaEntity();

        entity.setAreaID(model.getAreaID());
        entity.setName(model.getName());
        entity.setActive(model.getActive());

        return entity;

    }

}
