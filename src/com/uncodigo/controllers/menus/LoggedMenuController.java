package com.uncodigo.controllers.menus;

import com.uncodigo.auth.interfaces.IAuthService;
import com.uncodigo.accounts.interfaces.IBankAccountService;
import com.uncodigo.accounts.models.BankAccount;
import com.uncodigo.users.interfaces.IUserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LoggedMenuController {

    public static int show(
            IBankAccountService bankAccountService,
            IUserService userService,
            IAuthService authService
    ) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        int STEP_TO = -1;
        do {
            System.out.println("----------------- *** Menú VWallet *** -----------------");
            System.out.println("Usuario: " + authService.getUserLoggedIn().getName());
            System.out.println("Su saldo es: " + authService.getUserLoggedIn().getBankAccount().getBalance() + " (" + authService.getUserLoggedIn().getBankAccount().getCurrency().getCurrencyCode() + ")");
            System.out.println("------------------------ *** ------------------------");
            System.out.println("1. Logout");
            System.out.println("2. Ver saldo");
            System.out.println("3. Depositar");
            System.out.println("4. Retirar");
            System.out.println("5. Transferir");
            System.out.println("6. Ver transacciones");
            System.out.println("7. Terminar la aplicación");
            System.out.print("Ingrese una opción: ");
            try {
                option = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("---------------------- *** ---------------------");
                System.out.println("Opción no válida");
                System.out.println("---------------------- *** ---------------------");
                System.out.println();
                scanner = new Scanner(System.in);
                continue;
            }
            System.out.println("-----------------------------------------------------------");
            System.out.println();
            switch (option) {
                case 1:
                    System.out.println("Hasta luego " + authService.getUserLoggedIn().getName());
                    authService.logout();
                    STEP_TO = 1;
                    option = 7;
                    break;
                case 2:
                    System.out.println("Saldo: " + authService.getUserLoggedIn().getBankAccount().getBalance());
                    break;
                case 3:
                    System.out.println("----------------- *** Depositar *** -----------------");
                    System.out.print("Ingrese el monto a depositar: ");
                    double amountDeposit;
                    try {
                        amountDeposit = scanner.nextDouble();
                    } catch (Exception e) {
                        System.out.println("Monto no válido");
                        scanner = new Scanner(System.in);
                        break;
                    }
                    BankAccount bankAccount = authService.getUserLoggedIn().getBankAccount();
                    boolean resultDeposit = bankAccountService.deposit(bankAccount.getAccountNumber(), amountDeposit);
                    if (resultDeposit) {
                        System.out.println("Depósito realizado con éxito");
                        System.out.println("Saldo: " + authService.getUserLoggedIn().getBankAccount().getBalance());
                    } else {
                        System.out.println("No se pudo realizar el depósito");
                    }
                    System.out.println("----------------- *** --------- *** -----------------");
                    break;
                case 4:
                    System.out.println("----------------- *** Retirar *** -----------------");
                    System.out.print("Ingrese el monto a retirar: ");
                    double amountWithdraw;
                    try {
                        amountWithdraw = scanner.nextDouble();
                    } catch (Exception e) {
                        System.out.println("Monto no válido");
                        scanner = new Scanner(System.in);
                        break;
                    }
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
                    System.out.println("----------------- *** --------- *** -----------------");
                    System.out.println();
                    break;
                case 5:
                    System.out.println("----------------- *** Transferir *** -----------------");
                    System.out.println("*** Escriba exit para cancelar la transferencia");
                    System.out.print("Ingrese el email del destinatario: ");
                    String email = scanner.next();
                    if (email.equals("exit")) {
                        break;
                    }
                    BankAccount bankAccountFrom = authService.getUserLoggedIn().getBankAccount();
                    BankAccount bankAccountTo = userService.getUserByEmail(email).getBankAccount();
                    if (bankAccountTo == null) {
                        System.out.println("No se encontró el destinatario");
                        break;
                    }
                    System.out.print("Ingrese el monto a transferir: ");
                    double amountTransfer;
                    try {
                        amountTransfer = scanner.nextDouble();
                    } catch (Exception e) {
                        System.out.println("Monto no válido");
                        scanner = new Scanner(System.in);
                        break;
                    }
                    boolean resultTransfer = bankAccountService.transfer(bankAccountFrom.getAccountNumber(), bankAccountTo.getAccountNumber(), amountTransfer);

                    if (resultTransfer) {
                        System.out.println("Transferencia realizada con éxito");
                        System.out.println("Saldo: " + authService.getUserLoggedIn().getBankAccount().getBalance() + "(" + bankAccountFrom.getCurrency().getCurrencyCode() + ")");
                    } else {
                        System.out.println("No se pudo realizar la transferencia");
                    }
                    System.out.println("----------------- *** --------- *** -----------------");
                    System.out.println();
                    break;
                case 6:
                    System.out.println("----------------- *** Transacciones *** -----------------");
                    BankAccount bankAccountTransactions = authService.getUserLoggedIn().getBankAccount();
                    System.out.println("Fecha \t\t\t\t| Tipo \t\t| Monto");
                    System.out.println("--------------------------------------------");
                    bankAccountTransactions.getTransactions().forEach(transaction -> {
                        SimpleDateFormat dateTransaction = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        System.out.println(dateTransaction.format(transaction.getTimestamp()) + " | " + transaction.getType().getValue() + " \t| " + transaction.getAmount());
                    });
                    System.out.println("----------------- *** --------- *** -----------------");
                    System.out.println();
                    break;
                case 7:
                    STEP_TO = 0;
                default:
                    System.out.println("---------------------- *** ---------------------");
                    System.out.println("Opción no válida");
                    System.out.println("---------------------- *** ---------------------");
                    System.out.println();
                    break;
            }
        } while (option != 7);

        scanner.close();
        return STEP_TO;
    }

}
