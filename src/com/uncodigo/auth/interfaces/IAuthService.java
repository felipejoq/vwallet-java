package com.uncodigo.auth.interfaces;

import com.uncodigo.auth.dtos.LoginDto;
import com.uncodigo.auth.dtos.RegisterDto;
import com.uncodigo.users.models.User;

public interface IAuthService {
    boolean login(LoginDto loginDto);
    boolean register(RegisterDto registerDto);
    boolean logout();
    boolean isLoggedIn();
    User getUserLoggedIn();
}
