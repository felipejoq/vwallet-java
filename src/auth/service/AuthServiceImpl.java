package auth.service;

import auth.dtos.LoginDto;
import auth.dtos.RegisterDto;
import auth.interfaces.IAuthService;
import users.interfaces.IUserService;
import users.models.User;

public class AuthServiceImpl implements IAuthService {
    private User userLoggedIn;
    private boolean isLoggedIn;
    private final IUserService userService;

    public AuthServiceImpl(IUserService userService) {
        this.userService = userService;
        this.isLoggedIn = false;
    }

    @Override
    public boolean login(LoginDto loginDto) {
        User user = userService.getUserByEmail(loginDto.getEmail());
        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            this.userLoggedIn = user;
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        User user = userService.createUser(registerDto);
        if (user != null) {
            this.userLoggedIn = user;
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean logout() {
        if (this.isLoggedIn) {
            this.userLoggedIn = null;
            this.isLoggedIn = false;
            return true;
        }
        return false;
    }

    public User getUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(User userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
