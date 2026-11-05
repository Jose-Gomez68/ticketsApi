package com.ticket.code.tickets.login.model;

public class AuthTokenResponse {

    private String token;
    private String refreshToken;
    private long expiresIn;

    public AuthTokenResponse(
            String token,
            String refreshToken,
            long expiresIn) {

        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

}
