package com.ticket.code.tickets.login.model;

public record AuthResult (

    AuthTokenResponse tokens,
    AuthResponse user

) {}
