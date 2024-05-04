package com.uncodigo.users.interfaces;

import com.uncodigo.auth.dtos.RegisterDto;
import com.uncodigo.users.models.User;

import java.util.List;

/**
 * IUserService interface for User service
 */
public interface IUserService {

    /**
     * Get all users
     * @return List of users
     */
    List<User> getUsers();

    /**
     * Get user by id
     * @param id User id to search
     * @return User object
     */
    User getUserById(int id);

    /**
     * Get user by email
     * @param email User email to search
     * @return User object
     */
    User getUserByEmail(String email);

    /**
     * Create a new user
     * @param registerDto RegisterDto object
     * @return User object
     */
    User createUser(RegisterDto registerDto);

    /**
     * Update user
     * @param id User id to update
     * @param name User name
     * @param email User email
     * @return User object
     */
    User updateUser(int id, String name, String email);

    /**
     * Delete user
     * @param id User id to delete
     * @return boolean
     */
    boolean deleteUser(int id);
}
