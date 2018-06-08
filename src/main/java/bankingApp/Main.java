package bankingApp;

import bankingApp.user.UserLogin;
import bankingApp.user.UserRegistration;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1.Sign Up\n2.Sign In\n3.Close app");

            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                UserRegistration.registerUser();
            } else if (choice == 2) {
                UserLogin.loginUser();
            } else if(choice == 3){
                break;
            }
            else {
                System.out.println("Please enter a valid choice!");
            }
        }
    }

}