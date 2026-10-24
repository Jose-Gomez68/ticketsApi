package com.ticket.code.tickets.status.model;


public class StatusModel {

    private Long statusID;

    private String name;

    private String descrip;

    private String backgroundColor;

    private String dotColor;

    public Long getStatusID() {
        return statusID;
    }

    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getDotColor() {
        return dotColor;
    }

    public void setDotColor(String dotColor) {
        this.dotColor = dotColor;
    }
}
