package com.ticket.code.tickets.login.service.implement;

import com.ticket.code.tickets.login.model.AuthResponse;
import com.ticket.code.tickets.login.model.AuthResult;
import com.ticket.code.tickets.login.model.AuthTokenResponse;
import com.ticket.code.tickets.login.model.LoginRequest;
import com.ticket.code.tickets.login.service.IAuthService;
import com.ticket.code.tickets.refreshtoken.service.implement.RefreshTokenServiceJpa;
import com.ticket.code.tickets.security.service.JwtService;
import com.ticket.code.tickets.usuarios.entity.UserEntity;
import com.ticket.code.tickets.usuarios.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceJpa implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenServiceJpa refreshTokenService;

    public AuthServiceJpa(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtService jwtService,
            RefreshTokenServiceJpa refreshTokenService) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public AuthResult login(LoginRequest request) {

        // 1. Validar credenciales
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Obtener usuario
        UserEntity user = userRepository
                .findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );

        // 3. Generar tokens
        String token = jwtService.generateToken(user.getEmail());

        String refreshToken =
                refreshTokenService.createRefreshToken(user);

        // 4. Construir respuestas
        AuthTokenResponse tokens = new AuthTokenResponse(
                token,
                refreshToken,
                900
        );

        AuthResponse userResponse = buildUserResponse(user);

        return new AuthResult(tokens, userResponse);
    }

    @Override
    public AuthResult refresh(String refreshToken) {
        // 1. Validar y rotar refresh token
        var rotated =
                refreshTokenService.rotateRefreshToken(refreshToken);

        UserEntity user = rotated.user();

        // 2. Generar nuevo access token
        String newAccessToken =
                jwtService.generateToken(user.getEmail());

        // 3. Construir respuesta de tokens
        AuthTokenResponse tokens = new AuthTokenResponse(
                newAccessToken,
                rotated.refreshToken(),
                900
        );

        // 4. Construir respuesta del usuario
        AuthResponse userResponse = buildUserResponse(user);

        return new AuthResult(tokens, userResponse);
    }



    private AuthResponse buildUserResponse(UserEntity user) {

        Long areaID = user.getArea() != null
                ? user.getArea().getAreaID()
                : null;

        String areaName = user.getArea() != null
                ? user.getArea().getName()
                : null;

        String fullName = user.getName() + " "
                + user.getApPaterno() + " "
                + user.getApMaterno();

        return new AuthResponse(
                user.getUserID(),
                user.getUserName(),
                fullName,
                user.getEmail(),
                user.getRole().name(),
                areaID,
                areaName
        );
    }

}
