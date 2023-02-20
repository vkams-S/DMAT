package com.amazon.Services;

import com.amazon.DB.UserDAO;
import com.amazon.DB.UserShareDAO;
import com.amazon.Model.Account;
import com.amazon.Model.Share;
import com.amazon.Model.User;
import com.amazon.Model.UserShares;
import com.amazon.Session.DmatSession;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private UserService(){}
    private static UserService userService = new UserService();
    public static UserService getInstance(){
        return userService;
    }
    UserDAO dao = UserDAO.getInstance();
    UserShareDAO userShareDAO = UserShareDAO.getInstance();
    Scanner scanner = new Scanner(System.in);
    public int getAccountNumber(User user)
    {
        DmatSession.user= (dao.retrieve("SELECT * FROM [User] WHERE email ='"+user.email+"'")).get(0);
        return DmatSession.user.id;
    }

    public void getAccountDetails()
    {
        User user = DmatSession.user;
       Account account = new Account();
       account.AccountNumber = user.id;
       account.UserName = user.userName;
       account.AccountBalance = user.accountBalance;
       account.shares = userShareDAO.retrieve("SELECT * FROM [UserShares] WHERE userId="+user.id);
       account.prettyPrint();
    }

    public List<UserShares> viewShares()
    {
        User user = DmatSession.user;
        return userShareDAO.retrieve("SELECT * FROM [UserShares] WHERE userId="+user.id);

    }

    public List<UserShares> viewSharesByShareId(int shareId)
    {
        User user = DmatSession.user;
        return userShareDAO.retrieve("SELECT * FROM [UserShares] WHERE userId="+user.id+" AND shareId="+shareId);

    }

    public void depositMoney()
    {
        User user = DmatSession.user;
        System.out.println("Enter the amount to be added::");
        double amt = Double.parseDouble(scanner.nextLine());
        user = (dao.retrieve("SELECT * FROM [User] WHERE id="+user.id)).get(0);
        user.accountBalance+=amt;
       if(dao.update(user) >0)
       {
           System.out.println("Account updated Successfully. Current Balance is::"+user.accountBalance);
           DmatSession.user = (dao.retrieve("SELECT * FROM [User] WHERE id="+user.id)).get(0);
       }else{
           System.err.println("Something went wrong in updating account balance!");
       }
    }

    public void withdrawMoney()
    {
        System.out.println("Enter the amount to be withdrawn::");
        double amt = Double.parseDouble(scanner.nextLine());
        User user = DmatSession.user;
        if(user.accountBalance >= amt)
        {
            user.accountBalance-=amt;
            if(dao.update(user) >0)
            {
                System.out.println("Account updated Successfully. Current Balance is::"+user.accountBalance);
                DmatSession.user = (dao.retrieve("SELECT * FROM [User] WHERE id="+user.id)).get(0);
            }else{
                System.err.println("Something went wrong in updating account balance!");
            }
        }else{
            System.err.println("Not Enough Balance!");
        }

    }

}
