package bankingApp.customerOptions;

import bankingApp.InformationSerializer.BankAccountDetailsSerializer;
import bankingApp.InformationSerializer.CustomerDetailsSerializer;
import bankingApp.bankAccount.BankAccount;
import bankingApp.user.CustomerDetails;

public class CheckBalance {
    private String customerUserId;

    public CheckBalance(String userId){
        this.customerUserId = userId;
    }

    public void checkAccountBalance(){
        CustomerDetails customerDetails = CustomerDetailsSerializer.readCustomerDetails(customerUserId);
        if(customerDetails == null){
            System.out.println("You do not have any account available! Choose option 1 to open account");
            return;
        }

        String accountNumber = customerDetails.getCustomerAccountNumber();

        BankAccount bankAccount = BankAccountDetailsSerializer.readBankAccountDetails(accountNumber);
        System.out.println("Current balance: " + bankAccount.getBalance());

    }


}
