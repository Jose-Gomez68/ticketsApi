package com.ticket.code.tickets.ticket;

import com.ticket.code.tickets.kind.entity.KindEntity;
import com.ticket.code.tickets.Priority.entity.PriorityEntity;
import com.ticket.code.tickets.area.entity.AreaEntity;
import com.ticket.code.tickets.project.entity.ProjectEntity;
import com.ticket.code.tickets.status.entity.StatusEntity;
import com.ticket.code.tickets.usuarios.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticketID")
    private Long ticketID;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "createdDate", updatable = false)
    private Date createdDate;

    @Column(name = "updatedDate")
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kind_id")
    private KindEntity kind;// el tipo de ticket bug, sugerencia, error

    // Usuario que crea el ticket
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private UserEntity user;

    // Usuario al que se asigna el ticket
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignedID")
    private UserEntity assignedID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectID")
    private ProjectEntity project;// se refiere a un proyecto soft o aplicacion o programa que se use

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "areaID")
    private AreaEntity areaID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priorityID")
    private PriorityEntity priorityID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statusID")
    private StatusEntity statusID;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }

}
