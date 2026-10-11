package com.ticket.code.tickets.ticket.model;

import com.ticket.code.tickets.Priority.entity.PriorityEntity;
import com.ticket.code.tickets.Priority.model.PriorityModel;
import com.ticket.code.tickets.area.entity.AreaEntity;
import com.ticket.code.tickets.area.model.AreaModel;
import com.ticket.code.tickets.comment.entity.TicketCommentEntity;
import com.ticket.code.tickets.comment.model.TicketCommentModel;
import com.ticket.code.tickets.kind.entity.KindEntity;
import com.ticket.code.tickets.kind.model.KindModel;
import com.ticket.code.tickets.project.entity.ProjectEntity;
import com.ticket.code.tickets.project.model.ProjectModel;
import com.ticket.code.tickets.status.entity.StatusEntity;
import com.ticket.code.tickets.status.model.StatusModel;
import com.ticket.code.tickets.usuarios.entity.UserEntity;
import com.ticket.code.tickets.usuarios.model.UserModel;

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

    private KindModel kind;// el tipo de ticket bug, sugerencia, error

    // Usuario que crea el ticket
    private UserModel user;

    // Usuario al que se asigna el ticket
    private UserModel assignedID;

    private ProjectModel project;// se refiere a un proyecto soft o aplicacion o programa que se use

    private AreaModel areaID;

    private PriorityModel priorityID;

    private StatusModel statusID;

    private List<TicketCommentModel> comments = new ArrayList<>();

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

    public KindModel getKind() {
        return kind;
    }

    public void setKind(KindModel kind) {
        this.kind = kind;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserModel getAssignedID() {
        return assignedID;
    }

    public void setAssignedID(UserModel assignedID) {
        this.assignedID = assignedID;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public AreaModel getAreaID() {
        return areaID;
    }

    public void setAreaID(AreaModel areaID) {
        this.areaID = areaID;
    }

    public PriorityModel getPriorityID() {
        return priorityID;
    }

    public void setPriorityID(PriorityModel priorityID) {
        this.priorityID = priorityID;
    }

    public StatusModel getStatusID() {
        return statusID;
    }

    public void setStatusID(StatusModel statusID) {
        this.statusID = statusID;
    }

    public List<TicketCommentModel> getComments() {
        return comments;
    }

    public void setComments(List<TicketCommentModel> comments) {
        this.comments = comments;
    }
}
