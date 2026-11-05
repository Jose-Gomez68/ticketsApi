package com.ticket.code.tickets.refreshtoken.service.implement;

import com.ticket.code.tickets.refreshtoken.RefreshTokenEntity;
import com.ticket.code.tickets.refreshtoken.RefreshTokenRepository;
import com.ticket.code.tickets.refreshtoken.service.IRefreshTokenService;
import com.ticket.code.tickets.usuarios.entity.UserEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Service
@Primary
public class RefreshTokenServiceJpa implements IRefreshTokenService {

    private static final long REFRESH_EXPIRATION =
            7L * 24 * 60 * 60 * 1000;

    private final RefreshTokenRepository repository;

    private final SecureRandom secureRandom = new SecureRandom();

    public RefreshTokenServiceJpa(RefreshTokenRepository repository) {
        this.repository = repository;
    }

    // Generar token aleatorio
    private String generateToken() {

        byte[] bytes = new byte[64];

        secureRandom.nextBytes(bytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }

    // Obtener hash SHA-256 del token
    private String hashToken(String token) {

        try {

            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    token.getBytes(StandardCharsets.UTF_8)
            );

            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(hash);

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Error al generar hash del token", e
            );
        }
    }

    // Crear y guardar Refresh Token
    @Override
    @Transactional
    public String createRefreshToken(UserEntity user) {

        String token = generateToken();

        RefreshTokenEntity entity = new RefreshTokenEntity();

        entity.setUser(user);
        entity.setTokenHash(hashToken(token));
        entity.setExpirationDate(
                new Date(System.currentTimeMillis()
                        + REFRESH_EXPIRATION)
        );
        entity.setRevoked(false);

        repository.save(entity);

        return token;
    }

    // Validar, revocar y generar un nuevo Refresh Token
    @Override
    @Transactional
    public RotatedRefreshToken rotateRefreshToken(String token) {

        RefreshTokenEntity existing = repository
                .findByTokenHash(hashToken(token))
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Refresh Token inválido"
                        ));

        if (Boolean.TRUE.equals(existing.getRevoked())) {
            throw new IllegalArgumentException(
                    "Refresh Token revocado"
            );
        }

        if (existing.getExpirationDate().before(new Date())) {
            existing.setRevoked(true);

            throw new IllegalArgumentException(
                    "Refresh Token expirado"
            );
        }

        UserEntity user = existing.getUser();

        if (!Boolean.TRUE.equals(user.getActive())) {
            existing.setRevoked(true);

            throw new IllegalArgumentException(
                    "Usuario desactivado"
            );
        }

        // Invalidar el token anterior
        existing.setRevoked(true);

        // Crear uno nuevo
        String newToken = createRefreshToken(user);

        return new RotatedRefreshToken(user, newToken);
    }

    public record RotatedRefreshToken(
            UserEntity user,
            String refreshToken
    ) {}

}
