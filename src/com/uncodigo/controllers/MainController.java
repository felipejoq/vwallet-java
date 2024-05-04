package com.uncodigo.controllers;

import com.uncodigo.auth.interfaces.IAuthService;
import com.uncodigo.auth.service.AuthServiceImpl;
import com.uncodigo.accounts.interfaces.IBankAccountService;
import com.uncodigo.accounts.services.BankAccountServiceImpl;
import com.uncodigo.controllers.menus.GuestMenuController;
import com.uncodigo.controllers.menus.LoggedMenuController;
import com.uncodigo.users.services.UserServiceImpl;
import com.uncodigo.users.interfaces.IUserService;

/**
 * Main controller class
 * This class is the main controller of the application
 * Generates the main menu and redirects to the corresponding menu
 * depending on the user's choice and if is logged in or not
 */
public class MainController {

    IBankAccountService bankAccountService;
    IUserService userService;
    IAuthService authService;

    /**
     * Main controller constructor
     * @param bankAccountService Bank account service
     * @param userService User service
     * @param authService Auth service
     */
    public MainController(IBankAccountService bankAccountService, IUserService userService, IAuthService authService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
        this.authService = authService;
    }

    /**
     * Start method
     * @param stepTo Step to. Default is 1 (Guest menu), 2 (Logged menu).
     */
    public void start(int stepTo) {
        int STEP_TO = stepTo;

        do {
            switch (STEP_TO) {
                case 1 -> STEP_TO = GuestMenuController.show(
                        this.authService
                );
                case 2 -> STEP_TO = LoggedMenuController.show(
                        bankAccountService,
                        userService,
                        authService
                );
                default -> {
                }
            }
        } while (STEP_TO != 0);
    }
}
