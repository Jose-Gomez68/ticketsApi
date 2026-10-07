package com.ticket.code.tickets.project.service;

import com.ticket.code.tickets.project.model.ProjectModel;

import java.util.List;
import java.util.Optional;

public interface IProjectService {

    List<ProjectModel> allProject();

    ProjectModel save (ProjectModel project);

    ProjectModel update (ProjectModel project);

    ProjectModel deleteProject(Long projectID);

    Optional<ProjectModel> findByID(Long projectID);

    Optional<ProjectModel> findByNameIgnoreCase(String name);

}
