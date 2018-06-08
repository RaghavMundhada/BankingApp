import bankingApp.InformationSerializer.BankAccountDetailsSerializer;
import bankingApp.InformationSerializer.CustomerDetailsSerializer;
import bankingApp.bankAccount.BankAccount;
import bankingApp.customerOptions.Deposit;
import bankingApp.customerOptions.OpenAccount;
import bankingApp.customerOptions.Withdraw;
import bankingApp.user.CustomerDetails;
import bankingApp.user.UserRegistration;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BankAccountAppCheck {

    @Test
    public void removeMoneyTest() throws IOException {
        if (!UserRegistration.isRegisteredUser("mark", "facebook")) {
            UserRegistration.saveUserDetails("mark", "facebook");
        }

        Scanner sc = new Scanner(new File("src/test/resources/accountCreationTest.txt"));

        new OpenAccount("mark").openSimpleAccount(sc);

        Scanner sc1 = new Scanner(new File("src/test/resources/addMoneyTest.txt"));
        new Deposit("mark").deposit(sc1);

        Scanner sc2 = new Scanner(new File("src/test/resources/removeMoneyTest.txt"));
        new Withdraw("mark").withdraw(sc2);

        CustomerDetails customer = CustomerDetailsSerializer.readCustomerDetails("mark");

        String accountNumber = customer.getCustomerAccountNumber();

        BankAccount bankAccount = BankAccountDetailsSerializer.readBankAccountDetails(accountNumber);

        Assert.assertFalse(bankAccount.getBalance().equals(51));
    }

    @Test
    public void userSignUpTest() throws IOException {

        if (!UserRegistration.isRegisteredUser("mark", "facebook")) {
            UserRegistration.saveUserDetails("mark", "facebook");
        }
        if (!UserRegistration.isRegisteredUser("pichai", "google")) {
            UserRegistration.saveUserDetails("pichai", "google");
        }
        Assert.assertTrue(UserRegistration.isRegisteredUser("mark", "facebook"));
    }

    @Test
    public void addMoneyTest() throws IOException {
        if (!UserRegistration.isRegisteredUser("mark", "facebook")) {
            UserRegistration.saveUserDetails("mark", "facebook");
        }

        Scanner sc = new Scanner(new File("src/test/resources/accountCreationTest.txt"));

        new OpenAccount("mark").openSimpleAccount(sc);

        Scanner sc1 = new Scanner(new File("src/test/resources/addMoneyTest.txt"));
        new Deposit("mark").deposit(sc1);

        CustomerDetails customer = CustomerDetailsSerializer.readCustomerDetails("mark");

        String accountNumber = customer.getCustomerAccountNumber();

        BankAccount bankAccount = BankAccountDetailsSerializer.readBankAccountDetails(accountNumber);

        Assert.assertTrue(bankAccount.getBalance() == 101);
    }

    @Test
    public void userAccountCreationTest() throws IOException {
        if (!UserRegistration.isRegisteredUser("mark", "facebook")) {
            UserRegistration.saveUserDetails("mark", "facebook");
        }

        Scanner sc = new Scanner(new File("src/test/resources/accountCreationTest.txt"));

        new OpenAccount("mark").openSimpleAccount(sc);
    }
}
