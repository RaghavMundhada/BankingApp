package bankingApp.user.employee;

import bankingApp.user.customer.Customer;
import com.sun.deploy.util.StringUtils;

import java.util.regex.Pattern;

public class Employee extends Customer{
    public Employee(String userId, String name, String emailId, String mobileNumber) {
        super(userId, name, emailId, mobileNumber);
    }

    public static boolean isDetailsValid(Customer customer){
        if(!isValidEmail(customer.getEmailId())){
            System.out.println("Invalid emailId : " + customer.getEmailId());
            return false;
        }

        if(customer.getName().isEmpty()){
            System.out.println("Invalid name : " + customer.getName());
            return false;
        }

        if(!customer.getMobileNumber().matches("-?\\d+(\\.\\d+)?")){
            System.out.println("Invalid mobile number : " + customer.getMobileNumber());
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String emailId) {
        String emailReg = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern p = Pattern.compile(emailReg);

        return p.matcher(emailId).matches();
    }
}
