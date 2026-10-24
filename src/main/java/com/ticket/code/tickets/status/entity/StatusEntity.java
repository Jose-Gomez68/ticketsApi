package com.ticket.code.tickets.status.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusID")
    private Long statusID;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "descrip")
    private String descrip;

    @Column(name = "backgroundColor", length = 20)
    private String backgroundColor;

    @Column(name = "dotColor", length = 20)
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
