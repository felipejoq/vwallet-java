package users.services;

import auth.dtos.RegisterDto;
import bank.accounts.interfaces.IBankAccountService;
import bank.accounts.models.BankAccount;
import users.interfaces.IUserService;
import users.models.User;
import utils.strings.StringUtils;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class UserServiceImpl implements IUserService {

    private final List<User> users;
    private final IBankAccountService bankAccountService;

    public UserServiceImpl(IBankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
        // Initialize users list
        this.users = new ArrayList<>();
        // User vip initialized
        Currency currencyVip = Currency.getInstance("CLP");
        User userVip = new User(1, "Jane Doe", "jane@test.com", "123123");
        BankAccount bankAccountVip = this.bankAccountService
                .createAccount(StringUtils.getRandomString(6), userVip, currencyVip, 0.0);
        userVip.setBankAccount(bankAccountVip);
        userVip.setRole("VIP_ROLE");
        // User normal initialized
        Currency currency = Currency.getInstance("CLP");
        User user = new User(2, "John Doe", "john@test.com", "123123");
        BankAccount bankAccount = this.bankAccountService
                .createAccount(StringUtils.getRandomString(6), user, currency, 0.0);
        user.setBankAccount(bankAccount);

        this.users.add(userVip);
        this.users.add(user);
    }


    @Override
    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public User getUserById(int id) {
        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public User createUser(RegisterDto registerDto) {
        Currency currency = Currency.getInstance(registerDto.getCurrencyCode());
        User user = new User(
                this.users.size() + 1,
                registerDto.getName(),
                registerDto.getEmail(),
                registerDto.getPassword()
        );
        BankAccount bankAccount = this.bankAccountService
                .createAccount(StringUtils.getRandomString(6), user, currency, 0.0);
        user.setBankAccount(bankAccount);

        this.users.add(user);
        return user;
    }

    @Override
    public User updateUser(int id, String name, String email) {
        User user = this.getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) {
        User user = this.getUserById(id);
        if (user != null) {
            this.users.remove(user);
        }
        return user != null;
    }
}
