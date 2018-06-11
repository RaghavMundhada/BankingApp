package bankingApp.dao;

import bankingApp.database.DatabaseUtil;
import bankingApp.user.CustomerDetails;

public class CustomerDaoImplementation implements CustomerDAO {

    @Override
    public CustomerDetails getCustomerDetails(String customerId) {
        return DatabaseUtil.getCustomerDetailsFromDb(customerId);
    }

    @Override
    public void saveCustomerDetails(CustomerDetails customerDetails) {
        DatabaseUtil.storeCustomerDetailsToDb(customerDetails);
        return;
    }
}
