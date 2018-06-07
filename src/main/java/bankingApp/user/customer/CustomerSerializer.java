package bankingApp.user.customer;

import bankingApp.bankAccount.BankAccount;
import org.apache.log4j.Logger;

import java.io.*;

public class CustomerSerializer {
    private static Logger logger = Logger.getLogger(CustomerSerializer.class);

    public static void serializeCustomerObject(Customer customer){
        String userId = customer.getUserId();

        try {
            File newFile = new File(userId);
            FileOutputStream fileOutputStream = new FileOutputStream(userId,false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            Customer customer1 = new Customer(customer.getUserId(),customer.getName(),customer.getEmailId(),customer.getMobileNumber());

            objectOutputStream.writeObject(customer1);
            objectOutputStream.close();
            fileOutputStream.close();
            logger.info("Successfully serialized customer: "+ userId);
        } catch (Exception e) {
            logger.info("Exception while serializing customer : " + userId);
        }
    }


    public static Customer deSerializeCustomer(String userId){

        Customer customer1;
        try {
            FileInputStream fileInputStream = new FileInputStream(userId);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Customer customer = (Customer) objectInputStream.readObject();
            customer1 = new Customer(customer.getUserId(),customer.getName(),customer.getEmailId(),customer.getMobileNumber());

            fileInputStream.close();
            objectInputStream.close();

            logger.info("Successfully de serialized bank account number: "+ userId);

        } catch (Exception e) {
            logger.info("Exception while de serializing bank account : " + userId );
            return null;
        }

        return customer1;
    }
}