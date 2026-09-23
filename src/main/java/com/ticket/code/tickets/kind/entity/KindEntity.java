package com.ticket.code.tickets.kind.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticket.code.tickets.ticket.TicketEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "kind")
public class KindEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kindID")
    private Long kindID;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "kind", fetch = FetchType.LAZY)
    private List<TicketEntity> tickets = new ArrayList<>();

    @Column(name = "active")
    private Boolean active;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createdDate", updatable = false)
    private Date createdDate;

    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "updatedDate")
    private Date updatedDate;

    @Column(name = "deletedBy")
    private String deletedBy;

    @Column(name = "deletedDate")
    private Date deletedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }

    public Long getKindID() {
        return kindID;
    }

    public void setKindID(Long kindID) {
        this.kindID = kindID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreateBy() {
        return createdBy;
    }

    public void setCreateBy(String createBy) {
        this.createdBy = createBy;
    }

    public Date getCreateDate() {
        return createdDate;
    }

    public void setCreateDate(Date createDate) {
        this.createdDate = createDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }
}
