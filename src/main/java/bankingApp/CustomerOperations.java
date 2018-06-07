package bankingApp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Scanner;

public class CustomerOperations {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1.Register\n2.Login");

            choice = sc.nextInt();

            if (choice == 1) {
                registerUser(sc);
            } else if (choice == 2) {
                login(sc);
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private static void login(Scanner sc) throws IOException {
        System.out.println("Enter userId : ");
        String userId = sc.nextLine();
        System.out.println("Enter password : ");
        String password = sc.nextLine();

        if (isRegisteredUser(userId, password)) {
            int choice;
            boolean flag = true;
            while (flag) {
                System.out.println("1.Open Account\n2.Deposit\n3.Withdraw\n4.Transfer Funds\n5.Logout");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        openAccount(userId, sc);
                    case 2:
                        deposit(userId, sc);
                        break;
                    case 3:
                        withDraw(userId, sc);
                        break;
                    case 4:
                        transferFunds(userId, sc);
                        break;
                    case 5:
                        flag = false;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Invalid Credentials!");
        }
    }

    private static void openAccount(String userId, Scanner sc) {
        System.out.println("1.Simple Account\n2.Joint Account");
        int choice = sc.nextInt();

        switch (choice){
            case 1:

        }
    }

    private static void transferFunds(String userId, Scanner sc) {
        //write your code here;
    }

    private static void withDraw(String userId, Scanner sc) {
        //write your code here;
    }

    private static void deposit(String userId, Scanner sc) {
        //write your code here;
    }

    private static boolean isRegisteredUser(String userId, String password) throws IOException {
        HashMap<String, String> registeredUser = getRegisteredUser();

        if (registeredUser.containsKey(userId)) {
            if (password.equals(registeredUser.get(userId)))
                return true;
        }
        return false;
    }

    private static void registerUser(Scanner sc) throws IOException {
        HashMap<String, String> registeredUsers = getRegisteredUser();
        while (true) {
            System.out.println("Enter userId : ");
            String userId = sc.nextLine();

            if (registeredUsers.containsKey(userId)) {
                System.out.println("UserId Already Exists");
                continue;
            }

            System.out.println("Enter password : ");
            String password = sc.nextLine();

            saveUser(userId, password);

            break;
        }
    }

    private static void saveUser(String userId, String password) {
        try {
            Files.write(Paths.get("registeredUsers.txt"), (userId + "\t" + password + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static HashMap<String, String> getRegisteredUser() throws IOException {
        HashMap<String, String> registeredUser = new HashMap<String, String>();
        File file = new File("registeredUsers.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] details = st.split("\t");
            registeredUser.put(details[0], details[1]);
        }

        return registeredUser;
    }
}