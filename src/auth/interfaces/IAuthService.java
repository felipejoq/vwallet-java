package auth.interfaces;

import auth.dtos.LoginDto;
import auth.dtos.RegisterDto;
import users.models.User;

public interface IAuthService {
    boolean login(LoginDto loginDto);
    boolean register(RegisterDto registerDto);
    boolean logout();
    boolean isLoggedIn();
    User getUserLoggedIn();
}
