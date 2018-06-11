package bankingApp.user;

import bankingApp.database.DatabaseUtil;

public class Employee extends CustomerDetails {
    public Employee(String customerUserId, String customerName, String customerEmailId, String customerMobileNumber) {
        super(customerUserId, customerName, customerEmailId, customerMobileNumber);
    }

    //Employ can view any customer details
    public static void viewCustomerDetail(String userId){
        CustomerDetails cd = DatabaseUtil.getCustomerDetailsFromDb(userId);

        if(cd==null){
            System.out.println("User doesn't exists");
        }

        System.out.println("name = " + cd.getCustomerName());
        System.out.println("account no. = " + cd.getCustomerAccountNumber());
        System.out.println("mobile no. = " + cd.getCustomerMobileNumber());
        System.out.println("email id = " + cd.getCustomerEmailId());
    }
}
