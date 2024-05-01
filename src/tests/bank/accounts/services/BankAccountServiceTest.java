package tests.bank.accounts.services;

import com.uncodigo.accounts.interfaces.IBankAccountService;
import com.uncodigo.accounts.models.BankAccount;
import com.uncodigo.accounts.services.BankAccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.uncodigo.users.models.User;
import com.uncodigo.strings.StringUtils;

import java.util.Currency;

public class BankAccountServiceTest {

    private IBankAccountService bankAccountService;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1, "Test name", "test@test.com", "123123");
        this.bankAccountService = new BankAccountServiceImpl();
    }

    @Test
    public void testCreateAccount() {
        BankAccount bankAccount = this.bankAccountService
                .createAccount(
                        StringUtils.getRandomString(6),
                        this.user,
                        Currency.getInstance("CLP"),
                        0.0
                );
        assert bankAccount != null;
        assert bankAccount.getAccountHolder().equals(this.user);
    }

    @Test
    public void testGetAccount() {
        BankAccount bankAccount = this.bankAccountService
                .createAccount(
                        StringUtils.getRandomString(6),
                        this.user,
                        Currency.getInstance("CLP"),
                        0.0
                );

        BankAccount account = this.bankAccountService.getAccount(bankAccount.getAccountNumber());
        assert account != null;
        assert account.getAccountHolder().equals(this.user);
    }

    @Test
    public void testDeposit() {
        BankAccount bankAccount = this.bankAccountService
                .createAccount(
                        StringUtils.getRandomString(6),
                        this.user,
                        Currency.getInstance("CLP"),
                        0.0
                );

        this.bankAccountService.deposit(bankAccount.getAccountNumber(), 1000.0);
        BankAccount account = this.bankAccountService.getAccount(bankAccount.getAccountNumber());
        assert account != null;
        assert account.getBalance() == 1000.0;
    }

    @Test
    public void testWithdraw() {
        BankAccount bankAccount = this.bankAccountService
                .createAccount(
                        StringUtils.getRandomString(6),
                        this.user,
                        Currency.getInstance("CLP"),
                        1000.0
                );

        this.bankAccountService.withdraw(bankAccount.getAccountNumber(), 500.0);
        BankAccount account = this.bankAccountService.getAccount(bankAccount.getAccountNumber());
        assert account != null;
        assert account.getBalance() == 500.0;
    }

    @Test
    public void testTransfer() {
        BankAccount bankAccount1 = this.bankAccountService
                .createAccount(
                        StringUtils.getRandomString(6),
                        this.user,
                        Currency.getInstance("CLP"),
                        1000.0
                );

        BankAccount bankAccount2 = this.bankAccountService
                .createAccount(
                        StringUtils.getRandomString(6),
                        this.user,
                        Currency.getInstance("CLP"),
                        0.0
                );

        this.bankAccountService.transfer(bankAccount1.getAccountNumber(), bankAccount2.getAccountNumber(), 500.0);
        BankAccount account1 = this.bankAccountService.getAccount(bankAccount1.getAccountNumber());
        BankAccount account2 = this.bankAccountService.getAccount(bankAccount2.getAccountNumber());
        assert account1 != null;
        assert account2 != null;
        assert account1.getBalance() == 500.0;
        assert account2.getBalance() == 500.0;
    }

}
