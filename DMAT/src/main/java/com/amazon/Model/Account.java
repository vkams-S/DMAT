package com.amazon.Model;

import java.util.List;

public class Account {
    public String UserName;
    public int AccountNumber;
    public double AccountBalance;

   public List<UserShares> shares;

    public Account() {
    }

    public Account(String userName, int accountNumber, double accountBalance, List<UserShares> shares) {
        UserName = userName;
        AccountNumber = accountNumber;
        AccountBalance = accountBalance;
        this.shares = shares;
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("User Name:\t\t"+UserName);
        System.out.println("Account Number:\t\t"+AccountNumber);
        System.out.println("Account Balance:\t\t"+AccountBalance);
        System.out.println("Share details::");
        for(UserShares s:shares)
        {
            System.out.println("Share Name:\t"+s.companyName);
            System.out.println("Number of Share:\t"+s.shareCount);
            System.out.println("Value per share:\t"+s.price);
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }
}
