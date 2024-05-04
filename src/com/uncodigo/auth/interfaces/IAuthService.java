package com.uncodigo.auth.interfaces;

import com.uncodigo.auth.dtos.LoginDto;
import com.uncodigo.auth.dtos.RegisterDto;
import com.uncodigo.users.models.User;

/**
 * This interface defines the methods that the AuthService class must implement.
 */
public interface IAuthService {
    /**
     * This method receives a LoginDto object and returns a boolean value.
     * @param loginDto: LoginDto object with the email and password of the user.
     * @return boolean: True if the user is logged in, false otherwise.
     */
    boolean login(LoginDto loginDto);

    /**
     * This method receives a RegisterDto object and returns a boolean value.
     * @param registerDto: RegisterDto object with the name, email and password of the user.
     * @return boolean: True if the user is registered and logged in, false otherwise.
     */
    boolean register(RegisterDto registerDto);

    /**
     * This method returns a boolean value.
     * @return boolean: True if the user is logged out, false otherwise.
     */
    boolean logout();

    /**
     * This method returns a boolean value.
     * @return boolean: True if the user is logged in, false otherwise.
     */
    boolean isLoggedIn();

    /**
     * This method returns a User object.
     * @return User: User object with the information of the user logged in.
     */
    User getUserLoggedIn();
}
