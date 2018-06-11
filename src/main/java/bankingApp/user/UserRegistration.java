package bankingApp.user;

import bankingApp.database.DatabaseUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class UserRegistration {

    public  static boolean isRegisteredUser(String userId, String password) throws IOException {
        String password2 = DatabaseUtil.getRegisteredUserFromDb(userId);

        if (password2 == null){
            System.out.println("User id does not exist!");
            return false;
        }

        if(password.equals(password2)){
            return true;
        }
        System.out.println("Password does not match !");
        return false;
    }

    public static void registerUser() throws IOException {
        Scanner scanner = new Scanner(System.in);

//        HashMap<String, String> registeredUsers = getRegisteredUserDetails();
        while (true) {
            System.out.println("Enter userId : ");
            String userId = scanner.nextLine();

            if (DatabaseUtil.getRegisteredUserFromDb(userId) != null) {
                System.out.println("UserId Already Exists");
                continue;
            }

            System.out.println("Enter password : ");
            String password = scanner.nextLine();

            saveUserDetails(userId, password);

            break;
        }

        System.out.println("User Registered Successfully!");
    }

    public static void saveUserDetails(String userId, String password) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter("registeredUsers.txt",true));
//        writer.write(userId+"\t"+password+"\n");
//        writer.close();
        DatabaseUtil.storeRegisteredUserToDb(userId,password);
    }

    public static HashMap<String, String> getRegisteredUserDetails() throws IOException {
        HashMap<String, String> registeredUser = new HashMap<String, String>();
        File file = new File("registeredUsers.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\t");
            registeredUser.put(details[0], details[1]);
        }

        return registeredUser;
    }
}
