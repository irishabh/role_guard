package com.Authentication.roleGuard.Services.Interfaces;

import com.Authentication.roleGuard.DTO.AuthResponse;
import com.Authentication.roleGuard.Models.Users;

public interface AuthService {

    AuthResponse authenticate(Users users);
}
