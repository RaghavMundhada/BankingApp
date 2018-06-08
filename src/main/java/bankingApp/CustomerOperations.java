package bankingApp;

import bankingApp.bankAccount.BankAccount;
import bankingApp.bankAccount.BankAccountSerializer;
import bankingApp.user.customer.Customer;
import bankingApp.user.customer.CustomerSerializer;
import bankingApp.user.employee.Employee;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Scanner;

public class CustomerOperations {
    private static Logger logger = Logger.getLogger(CustomerOperations.class);
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1.Register\n2.Login");

            choice = sc.nextInt();
            sc.nextLine();
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
                System.out.println("1.Open Account\n2.Check balance\n3.Deposit\n4.Withdraw\n5.Transfer Funds\n6.Logout");

                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        openAccount(userId, sc);
                        break;
                    case 2:
                        checkBalance(userId,sc);
                        break;
                    case 3:
                        deposit(userId, sc);
                        break;
                    case 4:
                        withDraw(userId, sc);
                        break;
                    case 5:
                        transferFunds(userId, sc);
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

    private static void checkBalance(String userId, Scanner sc) {
        Customer customer = CustomerSerializer.deSerializeCustomer(userId);
        if(customer == null){
            System.out.println("open account first motherfucker! ");
            return;
        }
        String accountNumber = customer.getAccountNumber();

        BankAccount bankAccount = BankAccountSerializer.deSerializeBankAccount(accountNumber);
        System.out.println("Current balance: " + bankAccount.getBalance());

    }

    private static void openAccount(String userId, Scanner sc) throws IOException {
        System.out.println("1.Simple Account\n2.Joint Account");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                createSimpleAccount(userId, sc);
                break;
            case 2:
                createJointAccount(userId, sc);
                break;

            default:
                System.out.println("Invalid option chosen!");
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
            System.out.println("Your account number is : "+ accountNumber+" , remember it mothefuckaaaa!");
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
        System.out.println("Enter user id");
        String userId2 = sc.nextLine();
        System.out.println("Enter name");
        String nameSecondary = sc.nextLine();
        System.out.println("Enter emailId");
        String emailIDSecondary = sc.nextLine();
        System.out.println("Enter mobile number");
        String mobileSecondary = sc.nextLine();

        Customer customerPrimary = new Customer(userId, namePrimary, emailIDPrimary, mobilePrimary);
        Customer customerSecondary = new Customer(userId2, nameSecondary, emailIDSecondary, mobileSecondary);

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
        int transactionId;
        try {
            transactionId = getTransactionId();
        } catch (IOException e) {
            logger.info("Error in getting transaction id from file!");
            e.printStackTrace();
            return;
        }


        String payeeAccountNumber ;
        Double transferAmount;

        System.out.println("Hello user, how are you!\n");
        System.out.println("Enter account number to transfer funds to : ");
        payeeAccountNumber = sc.nextLine();

        System.out.println("Enter amount to transfer: ");
        transferAmount = sc.nextDouble();

        Transaction transaction = new Transaction();
        transaction.setTransactionId(Integer.toString(transactionId));

        Customer customer = CustomerSerializer.deSerializeCustomer(userId);
        if(customer == null){
            System.out.println("open account first motherfucker! ");
            return;
        }
        transaction.setPayerId(userId);
        transaction.setPayerAccountNumber(customer.getAccountNumber());
        transaction.setPayeeAccountNumber(payeeAccountNumber);
        transaction.setTransactionAmount(transferAmount);
        TransactionUtil.transferFund(transaction);

        logger.info("Transaction completed!");

        try {
            saveTransactionId(transactionId + 1);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Error in writing to file!");
        }


    }

    private static void withDraw(String userId, Scanner sc) {
        //write your code here;
        int transactionId;
        try {
            transactionId = getTransactionId();
        } catch (IOException e) {
            logger.info("Error in getting transaction id from file!");
            e.printStackTrace();
            return;
        }

        System.out.println("Hello user, how are you today! \n Enter amount to withdraw from your account. " );
        Double amount = sc.nextDouble();

        Transaction transaction = new Transaction();
        transaction.setTransactionId(Integer.toString(transactionId));


        Customer customer = CustomerSerializer.deSerializeCustomer(userId);

        if(customer == null){
            System.out.println("open account first motherfucker! ");
            return;
        }

        String customerBankAccountNumber = customer.getAccountNumber();
        transaction.setPayerAccountNumber(customerBankAccountNumber);
        transaction.setTransactionAmount(amount);
        transaction.setPayerId(userId);
        TransactionUtil.withdrawAmount(transaction);
        logger.info("Transaction completed!");

        try {
            saveTransactionId(transactionId + 1);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Error in writing to file!");
        }


    }

    //TODO
    private static void deposit(String userId, Scanner sc) {
        //write your code here;
        int transactionId;
        try {
            transactionId = getTransactionId();
        } catch (IOException e) {
            logger.info("Error in getting transaction id from file!");
            e.printStackTrace();
            return;
        }

        System.out.println("Hello user, how are you today!\n Enter amount to deposit in your account." );
        Double amount = sc.nextDouble();

        System.out.println("Depositing amount in your account .... ");

        Transaction transaction = new Transaction();
        transaction.setTransactionId(Integer.toString(transactionId));

        Customer customer = CustomerSerializer.deSerializeCustomer(userId);

        if(customer == null){
            System.out.println("Open account first motherfucker! ");
            return;
        }

        String customerBankAccountNumber = customer.getAccountNumber();
        transaction.setPayeeAccountNumber(customerBankAccountNumber);
        transaction.setTransactionAmount(amount);
        transaction.setPayeeId(userId);

        TransactionUtil.depositAmount(transaction);

        logger.info("Transaction completed!");

        try {
            saveTransactionId(transactionId + 1);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Error in writing to file!");
        }


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

    private static void saveUser(String userId, String password) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("registeredUsers.txt",true));
        writer.write(userId+"\t"+password+"\n");
        writer.close();

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

    private static int getTransactionId() throws IOException {
        File file = new File("transactionsIds.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int id = 0;
        String line;
        while((line = reader.readLine())!=null){
            id = Integer.parseInt(line.trim());
        }
        return id;
    }

    private static void saveTransactionId(int id) throws IOException {
        File file = new File("transactionsIds.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(id+"\n");
        writer.close();
    }
}