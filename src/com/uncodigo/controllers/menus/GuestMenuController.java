package com.uncodigo.controllers.menus;

import com.uncodigo.auth.dtos.LoginDto;
import com.uncodigo.auth.dtos.RegisterDto;
import com.uncodigo.auth.interfaces.IAuthService;

import java.util.Scanner;

public class GuestMenuController {
    public static int show(
            IAuthService authService
    ) {
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        int STEP_TO = 0;
        do {
            System.out.println("---------------*** Menú de usuario invitado ***------------");
            System.out.println("*** 1. Login");
            System.out.println("*** 2. Register");
            System.out.println("*** 6. Salir");
            System.out.print("*** Ingrese una opción: ");
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
                    System.out.println("---------------*** Login ***------------");
                    System.out.println("*** Escriba exit para salir");
                    System.out.print("*** Ingrese su email: ");
                    String email = scanner.next();
                    if (email.equals("exit")) {
                        break;
                    }
                    System.out.print("*** Ingrese su contraseña: ");
                    String password = scanner.next();
                    if (password.equals("exit")) {
                        break;
                    }
                    LoginDto loginDto = new LoginDto(email, password);
                    boolean resultLogin = authService.login(loginDto);
                    System.out.println();
                    if (resultLogin) {
                        System.out.println("-------------------***----------------------");
                        System.out.println("\t\tBienvenido(a) " + authService.getUserLoggedIn().getName());
                        System.out.println("-------------------***----------------------");
                        System.out.println();
                        STEP_TO = 2;
                        option = 6;
                    } else {
                        System.out.println("Email o contraseña incorrectos");
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.println("--------------------------------------------");
                    System.out.println("Registro de usuario (Escriba exit para cancelar el registro)");
                    System.out.print("-> Ingrese su nombre: ");
                    String name = scanner.next();
                    if (name.equals("exit")) {
                        break;
                    }
                    System.out.print("-> Ingrese su email: ");
                    String emailRegister = scanner.next();
                    if (emailRegister.equals("exit")) {
                        break;
                    }
                    System.out.print("-> Ingrese su contraseña: ");
                    String passwordRegister = scanner.next();
                    if (passwordRegister.equals("exit")) {
                        break;
                    }
                    RegisterDto registerDto = new RegisterDto(name, emailRegister, passwordRegister);
                    boolean resultRegister = authService.register(registerDto);
                    if (resultRegister) {
                        System.out.println("Usuario registrado con éxito");
                        STEP_TO = 2;
                        option = 6;
                    } else {
                        System.out.println("No se pudo registrar el usuario");
                    }
                    break;
                case 6:
                    STEP_TO = 0;
                    break;
                default:
                    System.out.println("---------------------- *** ---------------------");
                    System.out.println("Opción no válida");
                    System.out.println("---------------------- *** ---------------------");
                    System.out.println();
                    break;
            }

            if(STEP_TO == 2) {
                break;
            }

        } while (option != 6);
        return STEP_TO;
    }
}
