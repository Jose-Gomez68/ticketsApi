package com.ticket.code.tickets.login.controller;


import com.ticket.code.tickets.login.model.*;
import com.ticket.code.tickets.login.service.implement.AuthServiceJpa;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "*")

public class AuthController {

    private final AuthServiceJpa authService;

    public AuthController(AuthServiceJpa authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @RequestBody LoginRequest request) {

        try {

            // 1. Autenticar usuario
            AuthResult result = authService.login(request);

            // 2. Obtener tokens
            AuthTokenResponse tokens = result.tokens();

            // 3. Crear cookie Access Token
            ResponseCookie accessCookie = ResponseCookie
                    .from("accessToken", tokens.getToken())
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Lax")
                    .path("/")
                    .maxAge(Duration.ofSeconds(tokens.getExpiresIn()))
                    .build();

            // 4. Crear cookie Refresh Token
            ResponseCookie refreshCookie = ResponseCookie
                    .from("refreshToken", tokens.getRefreshToken())
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Lax")
                    .path("/api/auth/refresh")
                    .maxAge(Duration.ofDays(7))
                    .build();

            // 5. Responder con información del usuario
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                    .body(new ApiResponse<>(
                            true,
                            "Inicio de sesión correcto",
                            result.user()
                    ));

        } catch (BadCredentialsException e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(
                            false,
                            "Correo o contraseña incorrectos",
                            null
                    ));

        } catch (DisabledException e) {

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(
                            false,
                            "El usuario se encuentra desactivado",
                            null
                    ));
        }
    }


    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponse>> refresh(
            @CookieValue(
                    name = "refreshToken",
                    required = false
            ) String refreshToken) {

        try {
            // 1. Validar existencia del refresh token
            if (refreshToken == null || refreshToken.isBlank()) {
                throw new IllegalArgumentException(
                        "Refresh token ausente"
                );
            }

            // 2. Renovar tokens
            AuthResult result = authService.refresh(refreshToken);

            AuthTokenResponse tokens = result.tokens();

            // 3. Crear nueva cookie Access Token
            ResponseCookie accessCookie = ResponseCookie
                    .from("accessToken", tokens.getToken())
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Lax")
                    .path("/")
                    .maxAge(Duration.ofSeconds(tokens.getExpiresIn()))
                    .build();

            // 4. Crear nueva cookie Refresh Token
            ResponseCookie refreshCookie = ResponseCookie
                    .from("refreshToken", tokens.getRefreshToken())
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Lax")
                    .path("/api/auth/refresh")
                    .maxAge(Duration.ofDays(7))
                    .build();

            // 5. Devolver usuario y establecer nuevas cookies
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                    .body(new ApiResponse<>(
                            true,
                            "Token renovado correctamente",
                            result.user()
                    ));

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(
                            false,
                            "La sesión expiró. Inicia sesión nuevamente.",
                            null
                    ));
        }
    }

}
