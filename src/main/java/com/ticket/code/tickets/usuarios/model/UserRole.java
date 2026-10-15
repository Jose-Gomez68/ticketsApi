package com.ticket.code.tickets.usuarios.model;

public enum UserRole {

    ADMIN, //Ver todos los tickets, crear y eliminar usuarios, administrar el sistema.
    SUPERVISOR, //Ver y supervisar tickets, administrar su área, pero no crear ni eliminar usuarios.
    AGENT, //Atender y solucionar los tickets asignados.
    REQUESTER //Crear tickets y consultar sus propios tickets.

}
