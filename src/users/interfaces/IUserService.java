package users.interfaces;

import auth.dtos.RegisterDto;
import users.models.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User getUserById(int id);
    User getUserByEmail(String email);
    User createUser(RegisterDto registerDto);
    User updateUser(int id, String name, String email);
    boolean deleteUser(int id);
}
