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

    /*@JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<TicketEntity> tickets = new ArrayList<>();*/

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

    /*public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }*/
}
