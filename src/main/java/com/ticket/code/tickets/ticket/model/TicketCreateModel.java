package com.ticket.code.tickets.ticket.model;

import java.util.Date;

public class TicketCreateModel {

    private Long ticketID;

    private String title;

    private String description;

    private Boolean active;

    private Date createdDate;

    private Date updatedDate;

    private Long kind;// el tipo de ticket bug, sugerencia, error

    // Usuario que crea el ticket
    private Long user;

    // Usuario al que se asigna el ticket
    private Long assignedID;

    private Long project;// se refiere a un proyecto soft o aplicacion o programa que se use

    private Long areaID;

    private Long priorityID;

    private Long statusID;

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

    public Long getKind() {
        return kind;
    }

    public void setKind(Long kind) {
        this.kind = kind;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getAssignedID() {
        return assignedID;
    }

    public void setAssignedID(Long assignedID) {
        this.assignedID = assignedID;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public Long getAreaID() {
        return areaID;
    }

    public void setAreaID(Long areaID) {
        this.areaID = areaID;
    }

    public Long getPriorityID() {
        return priorityID;
    }

    public void setPriorityID(Long priorityID) {
        this.priorityID = priorityID;
    }

    public Long getStatusID() {
        return statusID;
    }

    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }
}
