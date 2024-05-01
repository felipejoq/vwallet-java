package com.uncodigo.controllers.menus;

import com.uncodigo.auth.interfaces.IAuthService;
import com.uncodigo.accounts.interfaces.IBankAccountService;
import com.uncodigo.accounts.models.BankAccount;
import com.uncodigo.users.interfaces.IUserService;

import java.util.Scanner;

public class LoggedMenuController {

    public static int show(
            IBankAccountService bankAccountService,
            IUserService userService,
            IAuthService authService
    ) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("Bienvenido(a) a la VWallet");
            System.out.println("Usuario: " + authService.getUserLoggedIn().getName());
            System.out.println("Su saldo es: " + authService.getUserLoggedIn().getBankAccount().getBalance() + " (" + authService.getUserLoggedIn().getBankAccount().getCurrency().getCurrencyCode() + ")");
            System.out.println("1. Logout");
            System.out.println("2. Ver saldo");
            System.out.println("3. Depositar");
            System.out.println("4. Retirar");
            System.out.println("5. Transferir");
            System.out.println("6. Terminar la aplicación");
            System.out.print("Ingrese una opción: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Hasta luego " + authService.getUserLoggedIn().getName());
                    authService.logout();
                    break;
                case 2:
                    System.out.println("Saldo: " + authService.getUserLoggedIn().getBankAccount().getBalance());
                    break;
                case 3:
                    System.out.print("Ingrese el monto a depositar: ");
                    double amountDeposit = scanner.nextDouble();
                    BankAccount bankAccount = authService.getUserLoggedIn().getBankAccount();
                    boolean resultDeposit = bankAccountService.deposit(bankAccount.getAccountNumber(), amountDeposit);
                    if (resultDeposit) {
                        System.out.println("Depósito realizado con éxito");
                        System.out.println("Saldo: " + authService.getUserLoggedIn().getBankAccount().getBalance());
                    } else {
                        System.out.println("No se pudo realizar el depósito");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el monto a retirar: ");
                    double amountWithdraw = scanner.nextDouble();
                    BankAccount bankAccount1 = authService.getUserLoggedIn().getBankAccount();
                    try {
                        boolean resultWithdraw = bankAccountService.withdraw(bankAccount1.getAccountNumber(), amountWithdraw);
                        if (resultWithdraw) {
                            System.out.println("Retiro realizado con éxito");
                            System.out.println("Saldo: " + authService.getUserLoggedIn().getBankAccount().getBalance() + "(" + bankAccount1.getCurrency().getCurrencyCode() + ")");
                        } else {
                            System.out.println("No se pudo realizar el retiro");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Ingrese el email del destinatario: ");
                    String email = scanner.next();

                    BankAccount bankAccountFrom = authService.getUserLoggedIn().getBankAccount();
                    BankAccount bankAccountTo = userService.getUserByEmail(email).getBankAccount();
                    if (bankAccountTo == null) {
                        System.out.println("No se encontró el destinatario");
                        break;
                    }
                    System.out.print("Ingrese el monto a transferir: ");
                    double amountTransfer = scanner.nextDouble();
                    boolean resultTransfer = bankAccountService.transfer(bankAccountFrom.getAccountNumber(), bankAccountTo.getAccountNumber(), amountTransfer);

                    if (resultTransfer) {
                        System.out.println("Transferencia realizada con éxito");
                        System.out.println("Saldo: " + authService.getUserLoggedIn().getBankAccount().getBalance() + "(" + bankAccountFrom.getCurrency().getCurrencyCode() + ")");
                    } else {
                        System.out.println("No se pudo realizar la transferencia");
                    }

                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (option != 6 && authService.isLoggedIn());
        return option;
    }

}
