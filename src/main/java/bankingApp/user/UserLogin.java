package bankingApp.user;

import bankingApp.customerOptions.*;

import java.io.IOException;
import java.util.Scanner;

public class UserLogin {

    public static void loginUser() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter userId : ");
        String userId = scanner.nextLine();

        System.out.println("Enter password : ");
        String password = scanner.nextLine();

        if (UserRegistration.isRegisteredUser(userId, password)) {
            int choice;
            boolean flag = true;
            while (flag) {
                System.out.println("1.Open Account\n2.Check balance\n3.Deposit\n4.Withdraw\n5.TransferFunds Funds\n6.Logout");

                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        new OpenAccount(userId).openNewAccount();
                        break;
                    case 2:
                        new CheckBalance(userId).checkAccountBalance();
                        break;
                    case 3:
                        new Deposit(userId).deposit();
                        break;
                    case 4:
                        new Withdraw(userId).withdraw();
                        break;
                    case 5:
                        new TransferFunds(userId).transferFunds();
                        break;
                    case 6:
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Invalid Credentials!");
        }
    }
}
