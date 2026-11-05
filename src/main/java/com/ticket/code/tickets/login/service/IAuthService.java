package com.ticket.code.tickets.login.service;

import com.ticket.code.tickets.login.model.AuthResponse;
import com.ticket.code.tickets.login.model.AuthResult;
import com.ticket.code.tickets.login.model.LoginRequest;

public interface IAuthService {

    AuthResult login(LoginRequest request);

    AuthResult refresh(String refreshToken);

}
