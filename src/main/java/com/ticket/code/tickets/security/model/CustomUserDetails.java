package com.ticket.code.tickets.security.model;

import com.ticket.code.tickets.usuarios.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    // ID del usuario
    public Long getUserID() {
        return user.getUserID();
    }

    // Nombre de usuario
    public String getUserName() {
        return user.getUserName();
    }

    // Nombre completo
    public String getName() {
        return user.getName();
    }

    // Correo electrónico
    public String getEmail() {
        return user.getEmail();
    }

    // Rol del usuario
    public String getRole() {
        return user.getRole().name();
    }

    // Área asignada
    public Long getAreaID() {
        return user.getArea() != null
                ? user.getArea().getAreaID()
                : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList(
                new SimpleGrantedAuthority(
                        "ROLE_" + user.getRole().name()
                )
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {//aqui como no uso el username sino el email
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(user.getActive());
    }

}
