package com.ticket.code.tickets.login.controller;


import com.ticket.code.tickets.login.model.AuthResponse;
import com.ticket.code.tickets.login.model.LoginRequest;
import com.ticket.code.tickets.login.service.implement.AuthServiceJpa;
import com.ticket.code.tickets.utils.modelsutils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthServiceJpa authService;

    public AuthController(AuthServiceJpa authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @RequestBody LoginRequest request) {

        try {
            /*System.out.println("AUTH REQUEST: " + request.getEmail());
            System.out.println("AUTH REQUEST: " + request.getPassword());
            System.out.println(
                    new BCryptPasswordEncoder().encode("12345678")
            );*/
            AuthResponse auth = authService.login(request);

            //System.out.println("AUTH RESPONSE: " + auth);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Inicio de sesión correcto",
                            auth
                    )
            );

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

}
