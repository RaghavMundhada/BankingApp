package bankingApp.customerOptions;

import bankingApp.InformationSerializer.BankAccountDetailsSerializer;
import bankingApp.InformationSerializer.CustomerDetailsSerializer;
import bankingApp.bankAccount.BankAccount;
import bankingApp.user.CustomerDetails;
import bankingApp.utils.FieldUtils;

import java.io.*;
import java.util.Scanner;

public class OpenAccount {

    private String userId;

    public OpenAccount(String userId){
        this.userId = userId;
    }

    public void openNewAccount() throws IOException {
        System.out.println("1.Simple Account\n2.Joint Account");
        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                openSimpleAccount(scanner);
                break;
            case 2:
                openJointAccount();
                break;

            default:
                System.out.println("Invalid option chosen!");
        }
    }

    public void openSimpleAccount(Scanner scanner) throws IOException {

        System.out.println("Enter name of account holder");
        String ownerName = scanner.nextLine();
        System.out.println("Enter emailId of account holder");
        String ownerEmailID = scanner.nextLine();
        System.out.println("Enter mobile number of account holder");
        String mobileNumber = scanner.nextLine();

        CustomerDetails customerDetails = new CustomerDetails(userId, ownerName, ownerEmailID, mobileNumber);

        if (FieldUtils.isDetailsValid(customerDetails)) {
            System.out.println("Account opened successfully.");
            String accountNumber = getAccountNumber();
            System.out.println("Your account number is : "+ accountNumber+" , remember it mothefuckaaaa!");
            customerDetails.setCustomerAccountNumber(accountNumber);
            BankAccount b = new BankAccount(customerDetails, accountNumber);
            BankAccountDetailsSerializer.saveBankAccountDetails(b);
            CustomerDetailsSerializer.saveCustomerDetails(customerDetails);
        } else {
            System.out.println("Unable to open account.");
        }
    }

    private void openJointAccount() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter details of primary nominee :");
        System.out.println("Enter name");
        String namePrimary = scanner.nextLine();
        System.out.println("Enter emailId");
        String emailIDPrimary = scanner.nextLine();
        System.out.println("Enter mobile number");
        String mobilePrimary = scanner.nextLine();

        System.out.println("Enter details of secondary nominee :");
        System.out.println("Enter user id");
        String userId2 = scanner.nextLine();
        System.out.println("Enter name");
        String nameSecondary = scanner.nextLine();
        System.out.println("Enter emailId");
        String emailIDSecondary = scanner.nextLine();
        System.out.println("Enter mobile number");
        String mobileSecondary = scanner.nextLine();

        CustomerDetails customerPrimary = new CustomerDetails(userId, namePrimary, emailIDPrimary, mobilePrimary);
        CustomerDetails customerSecondary = new CustomerDetails(userId2, nameSecondary, emailIDSecondary, mobileSecondary);

        if (!FieldUtils.isDetailsValid(customerPrimary) || !FieldUtils.isDetailsValid(customerSecondary)) {
            System.out.println("Unable to open account.");
        }
        else{
            System.out.println("Account opened successfully.");
            String accountNumber = getAccountNumber();
            System.out.println("Your account number is : " + accountNumber);

            customerPrimary.setCustomerAccountNumber(accountNumber);
            customerSecondary.setCustomerAccountNumber(accountNumber);
            BankAccount bankAccount = new BankAccount(customerPrimary, accountNumber);
            BankAccountDetailsSerializer.saveBankAccountDetails(bankAccount);
            BankAccount bankAccountSecondary = new BankAccount(customerSecondary, accountNumber);
            BankAccountDetailsSerializer.saveBankAccountDetails(bankAccountSecondary);

            CustomerDetailsSerializer.saveCustomerDetails(customerPrimary);
            CustomerDetailsSerializer.saveCustomerDetails(customerSecondary);
        }
    }

    public static String getAccountNumber() throws IOException {
        File file = new File("AcountsCreatedTillDate.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //Read total account numbers till now
        String line;
        String accountNumber = "";
        while((line = reader.readLine())!=null){
            accountNumber = line.trim();
        }
        reader.close();
        // Update and write account number back to file
        BufferedWriter writer = new BufferedWriter(new FileWriter("AcountsCreatedTillDate.txt"));
        int updatedAccountNumber = Integer.parseInt(accountNumber) + 1;
        writer.write((updatedAccountNumber+"\n"));
        writer.flush();
        writer.close();
        return accountNumber;
    }


}
