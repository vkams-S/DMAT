package com.amazon.View;

import com.amazon.DB.UserDAO;
import com.amazon.Model.User;
import com.amazon.Services.AuthenticationService;
import com.amazon.Services.TransactionService;
import com.amazon.Services.UserService;
import com.amazon.Session.DmatSession;

import java.util.Date;
import java.util.Scanner;

public class Menu {
    AuthenticationService auth = AuthenticationService.getInstance();
    UserService userService = UserService.getInstance();
    TransactionService transactionService = TransactionService.getInstance();
    UserDAO userDAO = UserDAO.getInstance();
    Scanner scanner = new Scanner(System.in);
    public void showMenu()
    {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("Enter Your Choice: ");
        int iChoice = Integer.parseInt(scanner.nextLine());

        boolean result = false;

        User user = new User();
        if(iChoice==1)
        {
            System.out.println("Enter Your Name:");
            user.name=scanner.nextLine();
            System.out.println("Enter Your email:");
            user.email=scanner.nextLine();
            System.out.println("Enter password:");
            user.password=scanner.nextLine();
            System.out.println("Enter a user name:");
            user.userName=scanner.nextLine();
            System.out.println("Enter a Account name:");
            user.accountName=scanner.nextLine();
            System.out.println("Enter Your Phone:");
            user.phone=scanner.nextLine();
            result = auth.registerUser(user);
            if(result){
                System.out.println("Successfully registered::");
                System.out.println("Your Account Number is ::"+userService.getAccountNumber(user));
                user = userDAO.retrieve("SELECT * FROM [User] WHERE email='"+user.email+"'").get(0);
            }
        } else if(iChoice == 2)
        {
            System.out.println("Enter your Account Number ::");
            user.id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Your Password ::");
            user.password = scanner.nextLine();
            result = auth.loginUser(user);
        }else{
            System.out.println("Invalid choice...");
            System.out.println("Thank you for using DMAT App");
        }

        if(result){
            DmatSession.user = user;
            System.out.println("^^^^^^^^^^^^^^^^^^^");
            System.out.println("Welcome to User App");
            System.out.println("Hello, "+user.name);
            System.out.println("Its: "+new Date());
            System.out.println("^^^^^^^^^^^^^^^^^^^");
            boolean quit = false;

            while(true) {
                System.out.println("0. Quit");
                System.out.println("1. Display Account Details");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Buy shares");
                System.out.println("5. Sell shares");
                System.out.println("6. View Transaction Report");
                System.out.println("Select an Option");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice)
                {
                    case 0:
                        System.out.println("Thank you for using DMAT App!");
                        quit=true;
                        break;
                    case 1: userService.getAccountDetails();
                        break;
                    case 2: userService.depositMoney();
                        break;
                    case 3:userService.withdrawMoney();
                        break;
                    case 4:transactionService.BuyShare();
                        break;
                    case 5:transactionService.SellShare();
                        break;
                    case 6:transactionService.viewTransactionReport();
                        break;
                    default:
                        System.err.println("Invalid choice...");
                        break;
                }
                if(quit)
                {
                    break;
                }
            }
            }
        else {
            System.err.println("Account Authentication Failed!");
        }
        }


    }

