package com.ticket.code.tickets.status.model;

import com.ticket.code.tickets.ticket.entity.TicketEntity;

import java.util.ArrayList;
import java.util.List;

public class StatusWithTicketsModel {

    private Long statusID;

    private String name;

    private List<TicketEntity> tickets = new ArrayList<>();

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

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }
}
