package bankingApp;

import java.util.HashMap;
import java.util.Scanner;

public class CustomerOperations {

    public static void main(String[] args) {
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

    private static void login(Scanner sc) {
        System.out.println("Enter userId : ");
        String userId = sc.nextLine();
        System.out.println("Enter password : ");
        String password = sc.nextLine();

        if (isRegisteredUser(userId, password)) {
            int choice;
            boolean flag = true;
            while (flag){
                System.out.println("1.Open Account\n2.Deposit\n3.Withdraw\n4.Transfer Funds\n5.Logout");

                choice = sc.nextInt();

                switch (choice){
                    case 1:
                        openAccount();
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withDraw();
                        break;
                    case 4:
                        transferFunds();
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

    private static void openAccount() {
        //write your code here;
    }

    private static void transferFunds() {
        //write your code here;
    }

    private static void withDraw() {
        //write your code here;
    }

    private static void deposit() {
        //write your code here;
    }

    private static boolean isRegisteredUser(String userId, String password) {
        //write your code here;
        return true;
    }

    private static void registerUser(Scanner sc) {
        HashMap<String, String> registeredUsers = getRegisteredUser();
        while (true) {
            System.out.println("Enter userId : ");
            String userId = sc.nextLine();

            if (registeredUsers.containsKey(userId)) {
                System.out.println("UserId Already Exists");
            }

            System.out.println("Enter password : ");
            String password = sc.nextLine();

            saveUser(userId, password);

            break;
        }
    }

    private static void saveUser(String userId, String password) {
        //write your code here;
    }

    public static HashMap<String, String> getRegisteredUser() {
        HashMap<String, String> registeredUser = new HashMap<String, String>();
        //readUsers here;
        return registeredUser;
    }
}
