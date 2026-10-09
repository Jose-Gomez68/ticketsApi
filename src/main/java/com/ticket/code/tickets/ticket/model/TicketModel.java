package com.ticket.code.tickets.ticket.model;

import com.ticket.code.tickets.Priority.entity.PriorityEntity;
import com.ticket.code.tickets.area.entity.AreaEntity;
import com.ticket.code.tickets.comment.entity.TicketCommentEntity;
import com.ticket.code.tickets.kind.entity.KindEntity;
import com.ticket.code.tickets.project.entity.ProjectEntity;
import com.ticket.code.tickets.status.entity.StatusEntity;
import com.ticket.code.tickets.usuarios.entity.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketModel {

    private Long ticketID;

    private String title;

    private String description;

    private Boolean active;

    private Date createdDate;

    private Date updatedDate;

    private KindEntity kind;// el tipo de ticket bug, sugerencia, error

    // Usuario que crea el ticket
    private UserEntity user;

    // Usuario al que se asigna el ticket
    private UserEntity assignedID;

    private ProjectEntity project;// se refiere a un proyecto soft o aplicacion o programa que se use

    private AreaEntity areaID;

    private PriorityEntity priorityID;

    private StatusEntity statusID;

    private List<TicketCommentEntity> comments = new ArrayList<>();

    public Long getTicketID() {
        return ticketID;
    }

    public void setTicketID(Long ticketID) {
        this.ticketID = ticketID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public KindEntity getKind() {
        return kind;
    }

    public void setKind(KindEntity kind) {
        this.kind = kind;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getAssignedID() {
        return assignedID;
    }

    public void setAssignedID(UserEntity assignedID) {
        this.assignedID = assignedID;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public AreaEntity getAreaID() {
        return areaID;
    }

    public void setAreaID(AreaEntity areaID) {
        this.areaID = areaID;
    }

    public PriorityEntity getPriorityID() {
        return priorityID;
    }

    public void setPriorityID(PriorityEntity priorityID) {
        this.priorityID = priorityID;
    }

    public StatusEntity getStatusID() {
        return statusID;
    }

    public void setStatusID(StatusEntity statusID) {
        this.statusID = statusID;
    }

    public List<TicketCommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<TicketCommentEntity> comments) {
        this.comments = comments;
    }
}
