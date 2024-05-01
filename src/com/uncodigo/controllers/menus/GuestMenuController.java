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
        int option = 0;
        do {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("1000. Salir");
            System.out.print("Ingrese una opción: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Ingrese su email: ");
                    String email = scanner.next();
                    System.out.print("Ingrese su contraseña: ");
                    String password = scanner.next();
                    LoginDto loginDto = new LoginDto(email, password);
                    boolean resultLogin = authService.login(loginDto);
                    if (resultLogin) {
                        System.out.println("Bienvenido " + authService.getUserLoggedIn().getName());
                        option = 1000;
                    } else {
                        System.out.println("Email o contraseña incorrectos");
                    }
                    break;
                case 2:
                    System.out.println("Registro de usuario (Escriba exist para cancelar el registro)");
                    System.out.print("Ingrese su nombre: ");
                    String name = scanner.next();
                    if (name.equals("exist")) {
                        break;
                    }
                    System.out.print("Ingrese su email: ");
                    String emailRegister = scanner.next();
                    if (emailRegister.equals("exist")) {
                        break;
                    }
                    System.out.print("Ingrese su contraseña: ");
                    String passwordRegister = scanner.next();
                    if (passwordRegister.equals("exist")) {
                        break;
                    }
                    RegisterDto registerDto = new RegisterDto(name, emailRegister, passwordRegister);
                    boolean resultRegister = authService.register(registerDto);
                    if (resultRegister) {
                        System.out.println("Usuario registrado con éxito");
                        option = 1000;
                    } else {
                        System.out.println("No se pudo registrar el usuario");
                    }
                    break;
                case 1000:
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (option != 1000);
        return option;
    }
}
