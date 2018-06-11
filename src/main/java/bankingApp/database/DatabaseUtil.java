
package bankingApp.database;

import bankingApp.bankAccount.BankAccount;
import bankingApp.user.CustomerDetails;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class DatabaseUtil {
    private static Logger logger = Logger.getLogger(DatabaseUtil.class);

    private static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/banking_app";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "root";
    private static boolean DB_AUTOCOMMIT = true;
    private static Database database = null;

    static {
        try {
            database = new Database(DB_DRIVER, DB_URL, DB_USER, DB_PASSWORD, DB_AUTOCOMMIT);
        } catch (Exception e) {
            logger.info("Cannot connect to DB! ");
            e.printStackTrace();
        }
    }

    public static void storeCustomerDetailsToDb(CustomerDetails customerDetails) {
        CallableStatement callableStatement = null;

        try {
            callableStatement = database.
                    getCallableStatement("{call INSERT_USER_DETAILS(?,?,?,?,?)}");

            callableStatement.setString(1, customerDetails.getCustomerUserId());
            callableStatement.setString(2, customerDetails.getCustomerEmailId());
            callableStatement.setString(3, customerDetails.getCustomerMobileNumber());
            callableStatement.setString(4, customerDetails.getCustomerName());
            callableStatement.setString(5, customerDetails.getCustomerAccountNumber());

            ResultSet resultSet = callableStatement.executeQuery();

            callableStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void storeRegisteredUserToDb(String userId, String password) {
        CallableStatement callableStatement = null;
        try {
            callableStatement = database.getCallableStatement("{call INSERT_REGISTERED_USER(?,?)}");
            callableStatement.setString(1, userId);
            callableStatement.setString(2, password);
            ResultSet resultSet = callableStatement.executeQuery();
            logger.info("User registered!");
            callableStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CustomerDetails getCustomerDetailsFromDb(String customerId) {
        CustomerDetails customerDetails = null;

        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {

            callableStatement = database.
                    getCallableStatement("{call  GET_USER_DETAILS(?)}");

            callableStatement.setString(1, customerId);

            resultSet = callableStatement.executeQuery();
            if (resultSet == null) {
                return customerDetails;
            }
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String userId = resultSet.getString("user_id");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                String accountNumber = resultSet.getString("account_number");
                customerDetails = new CustomerDetails(userId, name, email, mobile);
                customerDetails.setCustomerAccountNumber(accountNumber);

            }


            callableStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerDetails;
    }

    public static String getRegisteredUserFromDb(String customerId) {
        String password = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {

            callableStatement = database.getCallableStatement("{call GET_REGISTERED_USER(?)}");
            callableStatement.setString(1, customerId);
            resultSet = callableStatement.executeQuery();
            if (resultSet == null)
                return null;

            while(resultSet.next()){
                password = resultSet.getString("password");
            }

            callableStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public static void storeBankAccountDetailsToDb(BankAccount bankAccount) {
        CallableStatement callableStatement = null;

        try {

            callableStatement = database.
                    getCallableStatement("{call INSERT_ACCOUNT_DETAILS(?,?,?)}");


            callableStatement.setString(1, bankAccount.getAccountOwnerId());
            callableStatement.setString(2, bankAccount.getAccountNumber());
            callableStatement.setDouble(3, bankAccount.getBalance());

            ResultSet resultSet = callableStatement.executeQuery();

            callableStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BankAccount getBankAccountDetailsFromDb(String accountNumber) {
        BankAccount bankAccount = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = database.
                    getCallableStatement("{call  GET_ACCOUNT_DETAILS(?)}");


            callableStatement.setString(1, accountNumber);

            resultSet = callableStatement.executeQuery();
            if (resultSet == null) {
                return bankAccount;
            }

            while (resultSet.next()) {
                String userId = resultSet.getString("user_id");
                Double balance = Double.parseDouble(resultSet.getString("balance"));
                bankAccount = new BankAccount(userId,accountNumber);
                bankAccount.setBalance(balance);

            }

            callableStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bankAccount;
    }

    public static void updateBankAccountBalance(String accountNumber, Double balance){
        CallableStatement callableStatement = null;

        try {

            callableStatement = database.
                    getCallableStatement("{call UPDATE_BALANCE(?,?)}");

            callableStatement.setString(1,accountNumber);
            callableStatement.setDouble(2, balance);

            ResultSet resultSet = callableStatement.executeQuery();

            callableStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CustomerDetails customerDetails = getCustomerDetailsFromDb("1");
        System.out.println(customerDetails.getCustomerAccountNumber());
        System.out.println(customerDetails.getCustomerName());
    }
}

