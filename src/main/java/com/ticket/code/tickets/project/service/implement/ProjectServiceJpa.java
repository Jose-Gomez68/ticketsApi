package com.ticket.code.tickets.project.service.implement;

import com.ticket.code.tickets.project.entity.ProjectEntity;
import com.ticket.code.tickets.project.model.ProjectModel;
import com.ticket.code.tickets.project.repository.ProjectRepository;
import com.ticket.code.tickets.project.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProjectServiceJpa implements IProjectService {

    @Autowired
    private ProjectRepository rep;

    @Override
    public List<ProjectModel> allProject() {
        return rep.findAll()
                .stream()
                .map(this::convertToModel)
                .toList();
    }

    @Override
    public ProjectModel save(ProjectModel project) {
        ProjectEntity entity;

        entity = convertToEntity(project);

        ProjectEntity savedEntity = rep.save(entity);

        return convertToModel(savedEntity);
    }

    @Override
    public ProjectModel update(ProjectModel project) {

        ProjectEntity projectResult = rep.save(convertToEntity(project));

        return convertToModel(projectResult);
    }

    @Override
    public ProjectModel deleteProject(Long projectID) {
        ProjectEntity project = rep.findByProjectID(projectID);

        project.setActive(true);

        ProjectEntity result = rep.save(project);

        return convertToModel(result);
    }

    @Override
    public Optional<ProjectModel> findByID(Long projectID) {

        ProjectEntity entity = rep.findByProjectID(projectID);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));

    }

    @Override
    public Optional<ProjectModel> findByNameIgnoreCase(String name) {

        ProjectEntity entity = rep.findByNameIgnoreCase(name);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(convertToModel(entity));
    }

    private ProjectModel convertToModel(ProjectEntity entity) {

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

    private ProjectEntity convertToEntity(ProjectModel model) {

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
