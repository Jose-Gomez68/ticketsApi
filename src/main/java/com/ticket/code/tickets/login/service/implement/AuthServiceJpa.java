package com.ticket.code.tickets.login.service.implement;

import com.ticket.code.tickets.login.model.AuthResponse;
import com.ticket.code.tickets.login.model.LoginRequest;
import com.ticket.code.tickets.login.service.IAuthService;
import com.ticket.code.tickets.security.service.JwtService;
import com.ticket.code.tickets.usuarios.entity.UserEntity;
import com.ticket.code.tickets.usuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceJpa implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthServiceJpa(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request) {

        // 1. Validar credenciales con Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Obtener información del usuario
        UserEntity user = userRepository
                .findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );

        // 3. Generar JWT
        String token = jwtService.generateToken(user.getEmail());

        // 4. Obtener área
        Long areaID = user.getArea() != null
                ? user.getArea().getAreaID()
                : null;

        // 5. Construir respuesta
        return new AuthResponse(
                token,
                user.getUserID(),
                user.getUserName(),
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                areaID
        );
    }
}
