package com.ticket.code.tickets.project.model;

import com.ticket.code.tickets.project.entity.ProjectEntity;

public class ProjectConverter {

    private ProjectConverter () {

    }

    public static ProjectModel convertToModel(ProjectEntity entity) {

        ProjectModel model = new ProjectModel();

        model.setProjectID(entity.getProjectID());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        //model.setTickets(entity.getTickets());
        model.setActive(entity.getActive());
        model.setCreatedBy(entity.getCreatedBy());
        model.setCreatedDate(entity.getCreatedDate());
        model.setUpdatedBy(entity.getUpdatedBy());
        model.setCreatedDate(entity.getCreatedDate());

        return model;

    }

    public static ProjectEntity convertToEntity(ProjectModel model) {

        ProjectEntity entity = new ProjectEntity();

        entity.setProjectID(model.getProjectID());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        //entity.setTickets(model.getTickets());
        entity.setActive(model.getActive());
        entity.setCreatedBy(model.getCreatedBy());
        entity.setCreatedDate(model.getCreatedDate());
        entity.setUpdatedBy(model.getUpdatedBy());
        entity.setCreatedDate(model.getCreatedDate());

        return entity;

    }

}
