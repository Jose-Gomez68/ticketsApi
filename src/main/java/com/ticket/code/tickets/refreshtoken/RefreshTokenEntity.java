package com.ticket.code.tickets.refreshtoken;

import com.ticket.code.tickets.usuarios.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "refreshTokens")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", nullable = false)
    private UserEntity user;

    @Column(nullable = false, unique = true, length = 64)
    private String tokenHash;

    @Column(nullable = false)
    private Date expirationDate;

    @Column(nullable = false)
    private Boolean revoked = false;

    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    public Long getRefreshTokenID() {
        return refreshTokenID;
    }

    public void setRefreshTokenID(Long refreshTokenID) {
        this.refreshTokenID = refreshTokenID;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
