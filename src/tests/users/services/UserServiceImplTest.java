package tests.users.services;

import bank.accounts.services.BankAccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import users.interfaces.IUserService;
import users.services.UserServiceImpl;

public class UserServiceImplTest {

    private IUserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(new BankAccountServiceImpl());
    }

    @Test
    public void testGetUsers() {
        assert (userService.getUsers().size() == 2);
    }

    @Test
    public void testGetUserById() {
        assert (userService.getUserById(1).getName().equals("Jane Doe"));
        assert (userService.getUserById(2).getName().equals("John Doe"));
    }

    @Test
    public void testGetUserByEmail() {
        assert (userService.getUserByEmail("jane@test.com").getName().equals("Jane Doe"));
        assert (userService.getUserByEmail("john@test.com").getName().equals("John Doe"));
    }

}
