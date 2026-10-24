package com.ticket.code.tickets.project.service;

import com.ticket.code.tickets.project.model.ProjectModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IProjectService {

    List<ProjectModel> allProject();

    Page<ProjectModel> allProjectPage(Integer page, Integer size);

    ProjectModel save (ProjectModel project);

    ProjectModel update (ProjectModel project);

    ProjectModel deleteProject(Long projectID);

    Optional<ProjectModel> findByID(Long projectID);

    Optional<ProjectModel> findByNameIgnoreCase(String name);

}
