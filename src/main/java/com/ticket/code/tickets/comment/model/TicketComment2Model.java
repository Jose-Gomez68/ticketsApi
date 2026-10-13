package com.ticket.code.tickets.comment.model;

import com.ticket.code.tickets.ticket.model.TicketModel;

import java.util.Date;

public class TicketComment2Model {

    private Long commentID;

    private String message;

    // Ticket al que pertenece el comentario
    private TicketModel ticket;

    // Usuario que escribió el comentario
    private Long user;

    private Date createdDate;

    private Date updatedDate;

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

    public TicketModel getTicket() {
        return ticket;
    }

    public void setTicket(TicketModel ticket) {
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
