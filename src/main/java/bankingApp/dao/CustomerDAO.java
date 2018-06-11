package bankingApp.dao;

import bankingApp.user.CustomerDetails;

public interface CustomerDAO {
    public CustomerDetails getCustomerDetails(String customerId);

    public void saveCustomerDetails(CustomerDetails customerDetails);

}
