package com.ticket.code.tickets.refreshtoken.service;

import com.ticket.code.tickets.refreshtoken.service.implement.RefreshTokenServiceJpa;
import com.ticket.code.tickets.usuarios.entity.UserEntity;

public interface IRefreshTokenService {

    // Crear y guardar Refresh Token
    String createRefreshToken(UserEntity user);

    // Validar, revocar y generar un nuevo Refresh Token
    RefreshTokenServiceJpa.RotatedRefreshToken rotateRefreshToken(String token);

}
