package controllers;

import auth.interfaces.IAuthService;
import auth.service.AuthServiceImpl;
import bank.accounts.interfaces.IBankAccountService;
import bank.accounts.services.BankAccountServiceImpl;
import controllers.menus.GuestMenuController;
import controllers.menus.LoggedMenuController;
import users.services.UserServiceImpl;
import users.interfaces.IUserService;

public class MainController {

    IBankAccountService bankAccountService;
    IUserService userService;
    IAuthService authService;

    public MainController() {
        this.bankAccountService = new BankAccountServiceImpl();
        this.userService = new UserServiceImpl(bankAccountService);
        this.authService = new AuthServiceImpl(userService);
    }


    public void start() {
        // Menu for the bank application
        int option;
        do {
            if (authService.isLoggedIn()) {
                option = LoggedMenuController.show(
                        bankAccountService,
                        userService,
                        authService
                );
            } else {
                option = GuestMenuController.show(
                        authService
                );
            }
        } while (option != 6);
    }
}
