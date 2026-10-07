package com.ticket.code.tickets.project.repository;

import com.ticket.code.tickets.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    ProjectEntity findByProjectID(Long projectID);

    ProjectEntity findByNameIgnoreCase(String name);

}
