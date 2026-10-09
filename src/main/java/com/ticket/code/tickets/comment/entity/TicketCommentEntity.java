package com.ticket.code.tickets.comment.entity;

import com.ticket.code.tickets.ticket.entity.TicketEntity;
import com.ticket.code.tickets.usuarios.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ticketComment")
public class TicketCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentID")
    private Long commentID;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "createdDate", nullable = false, updatable = false)
    private Date createdDate;

    @Column(name = "updatedDate")
    private Date updatedDate;

    // Ticket al que pertenece el comentario
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticketID", nullable = false)
    private TicketEntity ticket;

    // Usuario que escribió el comentario
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private UserEntity user;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }

}
