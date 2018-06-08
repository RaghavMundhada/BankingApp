package bankingApp.InformationSerializer;

import bankingApp.user.CustomerDetails;
import org.apache.log4j.Logger;

import java.io.*;

public class CustomerDetailsSerializer {
    private static Logger logger = Logger.getLogger(CustomerDetailsSerializer.class);


    public static CustomerDetails readCustomerDetails(String customerUserId){
        // De serializes customer class object to disk

        CustomerDetails customerDetails1;
        try {

            FileInputStream fileInputStream = new FileInputStream("CustomerData/" + customerUserId);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            CustomerDetails customerDetails = (CustomerDetails) objectInputStream.readObject();
            customerDetails1 = new CustomerDetails(customerDetails.getCustomerUserId(),customerDetails.getCustomerName(),customerDetails.getCustomerEmailId(),customerDetails.getCustomerMobileNumber());
            customerDetails1.setCustomerAccountNumber(customerDetails.getCustomerAccountNumber());
            fileInputStream.close();
            objectInputStream.close();

            logger.info("Successfully read customer details : "+ customerUserId);

        } catch (Exception e) {
            logger.info("Exception while reading customer details : " + customerUserId );
            return null;
        }

        return customerDetails1;
    }

    public static void saveCustomerDetails(CustomerDetails customer){
        //Serializes CustomerDetails class object to disk (Saves customer class details)

        String customerUserId = customer.getCustomerUserId();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("CustomerData/" + customerUserId,false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            CustomerDetails customer1 = new CustomerDetails(customer.getCustomerUserId(),customer.getCustomerName(),customer.getCustomerEmailId(),customer.getCustomerMobileNumber());
            customer1.setCustomerAccountNumber(customer.getCustomerAccountNumber());
            objectOutputStream.writeObject(customer1);
            objectOutputStream.close();
            fileOutputStream.close();

            logger.info("Successfully saved customer details : "+ customerUserId);
        } catch (Exception e) {
            logger.info("Exception while saving customer details : " + customerUserId);
        }
    }

}
