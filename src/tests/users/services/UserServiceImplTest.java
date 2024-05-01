package tests.users.services;

import com.uncodigo.auth.dtos.RegisterDto;
import com.uncodigo.accounts.services.BankAccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.uncodigo.users.interfaces.IUserService;
import com.uncodigo.users.models.User;
import com.uncodigo.users.services.UserServiceImpl;

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

    @Test
    public void testCreateUser() {
        assert (userService.createUser(new RegisterDto("Oscar Doe", "oscar@test.com", "123123")).getName().equals("Oscar Doe"));
    }

    @Test
    public void testUpdateUser() {
        User userUpdated = userService.updateUser(1, "Luis Doe", "luis@test.com");
        assert (userService.getUserById(1).getName().equals(userUpdated.getName()));
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(1);
        assert (userService.getUsers().size() == 1);
    }

    @Test
    public void testGetRoleUser() {
        assert (userService.getUserById(1).getRole().equals("VIP_ROLE"));
        assert (userService.getUserById(2).getRole().equals("USER_ROLE"));
    }

    @Test
    public void testGetBankAccountUser() {
        assert (userService.getUserById(1).getBankAccount().getAccountHolder().getName().equals("Jane Doe"));
        assert (userService.getUserById(2).getBankAccount().getAccountHolder().getName().equals("John Doe"));
    }

}
