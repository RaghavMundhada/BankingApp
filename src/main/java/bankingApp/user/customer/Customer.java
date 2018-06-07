package bankingApp.user.customer;

import bankingApp.bankAccount.AbstractAccount;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String userId;
    private String password;

    private List<AbstractAccount> account = new ArrayList<AbstractAccount>();


}
