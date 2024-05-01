package tests.auth.services;

import com.uncodigo.auth.dtos.LoginDto;
import com.uncodigo.auth.dtos.RegisterDto;
import com.uncodigo.auth.interfaces.IAuthService;
import com.uncodigo.auth.service.AuthServiceImpl;
import com.uncodigo.accounts.services.BankAccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.uncodigo.users.interfaces.IUserService;
import com.uncodigo.users.services.UserServiceImpl;

public class AuthServicesImplTest {

    private IAuthService authService;
    private LoginDto loginDto;
    private RegisterDto registerDto;

    @BeforeEach
    public void setUp() {
        IUserService userService = new UserServiceImpl(new BankAccountServiceImpl());
        authService = new AuthServiceImpl(userService);
        loginDto = new LoginDto("jane@test.com", "123123");
        registerDto = new RegisterDto("Luis Doe", "luis@test.com", "123123");
    }

    // Tests for login method
    @Test
    public void testLogin() {
        assert(authService.login(loginDto));
        assert(!authService.login(new LoginDto("ol@test.com", "123123")));
    }

    @Test
    public void testLogout() {
        authService.login(loginDto);
        assert(authService.logout());
    }

    @Test
    public void testRegister() {
        assert(authService.register(registerDto));
        assert(!authService.register(new RegisterDto("", "jane@test.com", "")));
    }

    @Test
    public void testGetLoggedInUser() {
        authService.login(loginDto);
        assert(authService.getUserLoggedIn() != null);
    }

}
