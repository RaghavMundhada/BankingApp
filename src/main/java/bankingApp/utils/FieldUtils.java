package bankingApp.utils;

import bankingApp.user.CustomerDetails;

public class FieldUtils {

    public static boolean isDetailsValid(CustomerDetails customer){
        if(!isValidEmail(customer.getCustomerEmailId())){
            System.out.println("Invalid emailId : " + customer.getCustomerEmailId() + ", Please use @ in your email!");
            return false;
        }

        if(customer.getCustomerName().isEmpty()){
            System.out.println("Invalid name : " + customer.getCustomerName());
            return false;
        }

        if(!customer.getCustomerMobileNumber().matches("-?\\d+(\\.\\d+)?")){
            System.out.println("Invalid mobile number : " + customer.getCustomerMobileNumber());
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String emailId) {
        if(emailId.contains("@")) return true;
        return false;
    }


}
