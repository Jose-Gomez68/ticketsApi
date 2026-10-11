package com.ticket.code.tickets.comment.entity;

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

    // Ticket al que pertenece el comentario
    @Column(name = "ticketID")
    private Long ticket;

    // Usuario que escribió el comentario
    @Column(name = "userID")
    private Long user;

    @Column(name = "createdDate", nullable = false, updatable = false)
    private Date createdDate;

    @Column(name = "updatedDate")
    private Date updatedDate;

    /*// Ticket al que pertenece el comentario
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticketID", nullable = false)
    private TicketEntity ticket;

    // Usuario que escribió el comentario
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private UserEntity user;*/

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTicket() {
        return ticket;
    }

    public void setTicket(Long ticket) {
        this.ticket = ticket;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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
}
