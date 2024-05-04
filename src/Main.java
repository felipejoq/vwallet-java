import com.uncodigo.accounts.interfaces.IBankAccountService;
import com.uncodigo.accounts.services.BankAccountServiceImpl;
import com.uncodigo.auth.interfaces.IAuthService;
import com.uncodigo.auth.service.AuthServiceImpl;
import com.uncodigo.controllers.MainController;
import com.uncodigo.users.interfaces.IUserService;
import com.uncodigo.users.services.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        /**
         * Initialize services
         * DEFAULT_INIT_STEP is the initial step to start the application
         * bankAccountService is an instance of BankAccountServiceImpl
         * userService is an instance of UserServiceImpl
         * authService is an instance of AuthServiceImpl
         * mainController is an instance of MainController
         * mainController calls the start method with the DEFAULT_INIT_STEP
         */
        int DEFAULT_INIT_STEP = 1;

        IBankAccountService bankAccountService = new BankAccountServiceImpl();
        IUserService userService = new UserServiceImpl(bankAccountService);
        IAuthService authService = new AuthServiceImpl(userService);

        MainController mainController = new MainController(bankAccountService, userService, authService);
        mainController.start(DEFAULT_INIT_STEP);
    }
}