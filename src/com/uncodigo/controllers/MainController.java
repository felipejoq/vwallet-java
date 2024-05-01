package com.uncodigo.controllers;

import com.uncodigo.auth.interfaces.IAuthService;
import com.uncodigo.auth.service.AuthServiceImpl;
import com.uncodigo.accounts.interfaces.IBankAccountService;
import com.uncodigo.accounts.services.BankAccountServiceImpl;
import com.uncodigo.controllers.menus.GuestMenuController;
import com.uncodigo.controllers.menus.LoggedMenuController;
import com.uncodigo.users.services.UserServiceImpl;
import com.uncodigo.users.interfaces.IUserService;

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
        // Menu for the com.uncodigo.bank application
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
