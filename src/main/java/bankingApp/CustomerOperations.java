package bankingApp;

import bankingApp.bankAccount.BankAccount;
import bankingApp.bankAccount.BankAccountSerializer;
import bankingApp.user.customer.Customer;
import bankingApp.user.customer.CustomerSerializer;
import bankingApp.user.employee.Employee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Scanner;

public class CustomerOperations {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1.Register\n2.Login");

            choice = sc.nextInt();

            if (choice == 1) {
                registerUser(sc);
            } else if (choice == 2) {
                login(sc);
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private static void login(Scanner sc) throws IOException {
        System.out.println("Enter userId : ");
        String userId = sc.nextLine();
        System.out.println("Enter password : ");
        String password = sc.nextLine();

        if (isRegisteredUser(userId, password)) {
            int choice;
            boolean flag = true;
            while (flag) {
                System.out.println("1.Open Account\n2.Deposit\n3.Withdraw\n4.Transfer Funds\n5.Logout");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        openAccount(userId, sc);
                    case 2:
                        deposit(userId, sc);
                        break;
                    case 3:
                        withDraw(userId, sc);
                        break;
                    case 4:
                        transferFunds(userId, sc);
                        break;
                    case 5:
                        flag = false;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Invalid Credentials!");
        }
    }

    private static void openAccount(String userId, Scanner sc) throws IOException {
        System.out.println("1.Simple Account\n2.Joint Account");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                createSimpleAccount(userId, sc);
            case 2:
                createJointAccount(userId, sc);
        }
    }

    private static void createSimpleAccount(String userId, Scanner sc) throws IOException {
        System.out.println("Enter name");
        String name = sc.nextLine();
        System.out.println("Enter emailId");
        String emailID = sc.nextLine();
        System.out.println("Enter mobile number");
        String mobile = sc.nextLine();

        Customer customer = new Customer(userId, name, emailID, mobile);

        if (Employee.isDetailsValid(customer)) {
            System.out.println("Account opened successfully.");
            String accountNumber = getAccountNumber();
            customer.setAccountNumber(accountNumber);
            BankAccount b = new BankAccount(customer, accountNumber);
            BankAccountSerializer.serializeBankAccount(b);
            CustomerSerializer.serializeCustomerObject(customer);
        } else {
            System.out.println("Unable to open account.");
        }
    }

    private static void createJointAccount(String userId, Scanner sc) throws IOException {
        System.out.println("Enter details of primary nominee :");
        System.out.println("Enter name");
        String namePrimary = sc.nextLine();
        System.out.println("Enter emailId");
        String emailIDPrimary = sc.nextLine();
        System.out.println("Enter mobile number");
        String mobilePrimary = sc.nextLine();

        System.out.println("Enter details of secondary nominee :");
        System.out.println("Enter name");
        String nameSecondary = sc.nextLine();
        System.out.println("Enter emailId");
        String emailIDSecondary = sc.nextLine();
        System.out.println("Enter mobile number");
        String mobileSecondary = sc.nextLine();

        Customer customerPrimary = new Customer(userId, namePrimary, emailIDPrimary, mobilePrimary);
        Customer customerSecondary = new Customer(userId, nameSecondary, emailIDSecondary, mobileSecondary);

        if (Employee.isDetailsValid(customerPrimary) && Employee.isDetailsValid(customerSecondary)) {
            System.out.println("Account opened successfully.");
            String accountNumber = getAccountNumber();
            customerPrimary.setAccountNumber(accountNumber);
            customerSecondary.setAccountNumber(accountNumber);
            BankAccount b = new BankAccount(customerPrimary, accountNumber);
            BankAccountSerializer.serializeBankAccount(b);
            BankAccount b1 = new BankAccount(customerSecondary, accountNumber);
            BankAccountSerializer.serializeBankAccount(b1);
            CustomerSerializer.serializeCustomerObject(customerPrimary);
            CustomerSerializer.serializeCustomerObject(customerSecondary);
        } else {
            System.out.println("Unable to open account.");
        }
    }

    private static void transferFunds(String userId, Scanner sc) {
        //write your code here;
    }

    private static void withDraw(String userId, Scanner sc) {
        //write your code here;
    }

    private static void deposit(String userId, Scanner sc) {
        //write your code here;
    }

    private static boolean isRegisteredUser(String userId, String password) throws IOException {
        HashMap<String, String> registeredUser = getRegisteredUser();

        if (registeredUser.containsKey(userId)) {
            if (password.equals(registeredUser.get(userId)))
                return true;
        }
        return false;
    }

    private static void registerUser(Scanner sc) throws IOException {
        HashMap<String, String> registeredUsers = getRegisteredUser();
        while (true) {
            System.out.println("Enter userId : ");
            String userId = sc.nextLine();

            if (registeredUsers.containsKey(userId)) {
                System.out.println("UserId Already Exists");
                continue;
            }

            System.out.println("Enter password : ");
            String password = sc.nextLine();

            saveUser(userId, password);

            break;
        }

        System.out.println("User Registered Successfully!");
    }

    private static void saveUser(String userId, String password) {
        try {
            Files.write(Paths.get("registeredUsers.txt"), (userId + "\t" + password + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static HashMap<String, String> getRegisteredUser() throws IOException {
        HashMap<String, String> registeredUser = new HashMap<String, String>();
        File file = new File("registeredUsers.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] details = st.split("\t");
            registeredUser.put(details[0], details[1]);
        }

        return registeredUser;
    }

    public static String getAccountNumber() throws IOException {
        File file = new File("AcountsCreatedTillDate.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String accountNumber = br.readLine();
        br.close();

        Files.write(Paths.get("AcountsCreatedTillDate.txt"), String.valueOf(Integer.parseInt(accountNumber) + 1).getBytes(), StandardOpenOption.WRITE);
        return accountNumber;
    }
}