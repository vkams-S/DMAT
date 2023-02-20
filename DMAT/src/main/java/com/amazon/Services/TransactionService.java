package com.amazon.Services;

import com.amazon.DB.TransactionDAO;
import com.amazon.DB.UserDAO;
import com.amazon.DB.UserShareDAO;
import com.amazon.Model.Share;
import com.amazon.Model.Transaction;
import com.amazon.Model.User;
import com.amazon.Model.UserShares;
import com.amazon.Session.DmatSession;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class TransactionService {
    private TransactionService(){}
    private static TransactionService service = new TransactionService();
    public static TransactionService getInstance(){
        return service;
    }
    UserDAO dao = UserDAO.getInstance();
    UserShareDAO userShareDAO = UserShareDAO.getInstance();
    ShareService shareService = ShareService.getInstance();
    TransactionDAO tDao = TransactionDAO.getInstance();
    UserService userService = UserService.getInstance();
    Scanner scanner = new Scanner(System.in);

    public void BuyShare()
    {
        User user = DmatSession.user;
        //1. Display all the available shares
           shareService.displayAllShares();
        //2. Ask user to enter the share id in which they are interested
        System.out.println("Enter share ID which you want to buy:");
        int shareId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter number of unit you want to buy::");
        int unit = Integer.parseInt(scanner.nextLine());
        // 3. check account balance if they have enough balance to buy the selected share]
        Share share = shareService.getShare(shareId);
        if(user.accountBalance>=(share.price*unit))
        {
            //4. update the transaction table
            Transaction object = new Transaction();
            object.userId = DmatSession.user.id;
            object.shareId = shareId;
            object.shareCount = unit;
            object.pricePerShare = share.price;
            object.sstCharges = DmatSession.sstCharges;
            object.transactionCharges = DmatSession.transactionCharges;
            object.type = 1;

            int transactionUpdate =tDao.insert(object);;

            // 5. update userShare table
            if(transactionUpdate >0)
            {
                UserShares userShares = new UserShares();
                userShares.userId = user.id;
                userShares.shareId = shareId;
                userShares.companyName = share.companyName;
                userShares.shareCount = unit;
                userShares.price = share.price;

                int userShareUpdate = 0;

                if(userService.viewSharesByShareId(shareId).size()>0)
                {
                    userShares.id = userService.viewSharesByShareId(shareId).get(0).id;
                    userShares.shareCount = unit + userService.viewSharesByShareId(shareId).get(0).shareCount;
                    userShareUpdate= userShareDAO.update(userShares);
                }else {
                    userShareUpdate = userShareDAO.insert(userShares);
                }
                if(userShareUpdate>0)
                {
                    //6. update the AccountBalance of
                    if(share.price*unit*DmatSession.transactionCharges<100)
                    {
                        user.accountBalance-=((share.price*unit)+(share.price*unit*100)+(share.price*unit*DmatSession.sstCharges));
                    }else{
                        user.accountBalance-=((share.price*unit)+(share.price*unit*DmatSession.transactionCharges)+(share.price*unit*DmatSession.sstCharges));
                    }

                    int balanceUpdate = dao.update(user);
                    if(balanceUpdate>0)
                    {
                        System.out.println("Transaction successful.");
                    }else {
                        System.err.println("Transaction failed!");
                    }
                }else{
                    System.err.println("Transaction failed!");
                }
            }else{
                System.err.println("Transaction failed!");
            }
        }
        else{
            System.err.println("Not enough account balance to purchase the share!");
        }

    }

    public void SellShare()
    {
        User user = DmatSession.user;
        HashMap<Integer,Integer> onwedShares = new HashMap<>();
        //1. Display all the available User shares
        List<UserShares> shares = userService.viewShares();
        for(UserShares s : shares )
        {
            onwedShares.put(s.shareId,s.shareCount);
            s.prettyPrint();
        }
        //2. Ask user to enter the share id in which they are interested
        System.out.println("Enter share ID which you want to Sell:");
        int shareId = Integer.parseInt(scanner.nextLine());
        if(!onwedShares.containsKey(shareId))
        {
            System.err.println("You do not own this share!");
            return;
        }
        System.out.println("Enter number of unit you want to Sell::");
        int unit = Integer.parseInt(scanner.nextLine());
        if(onwedShares.get(shareId)<unit)
        {
            System.err.println("You do not own "+unit+" of this share!");
            return;
        }
        // 3. check account balance if they have enough balance to buy the selected share]
        Share share = shareService.getShare(shareId);
            //4. update the transaction table
            Transaction object = new Transaction();
            object.userId = DmatSession.user.id;
            object.shareId = shareId;
            object.shareCount = unit;
            object.pricePerShare = share.price;
            object.sstCharges = DmatSession.sstCharges;
            object.transactionCharges = DmatSession.transactionCharges;
            object.type = 2;

            int transactionUpdate = tDao.insert(object);
            // 5. update userShare table
            if(transactionUpdate >0)
            {
                UserShares userShares = new UserShares();
                userShares.userId = user.id;
                userShares.shareId = shareId;
                userShares.companyName = share.companyName;
                userShares.shareCount = unit;
                userShares.price = share.price;

                int userShareUpdate = 0;

                if(userService.viewSharesByShareId(shareId).size()>0)
                {
                    userShares.id = userService.viewSharesByShareId(shareId).get(0).id;
                    userShares.shareCount = userService.viewSharesByShareId(shareId).get(0).shareCount - unit;
                    userShareUpdate= userShareDAO.update(userShares);
                }else {
                    userShareUpdate = userShareDAO.insert(userShares);
                }
                if(userShareUpdate>0)
                {
                    //6. update the AccountBalance of User
                    if(share.price*unit*DmatSession.transactionCharges<100)
                    {
                        user.accountBalance+=((share.price*unit)-(share.price*unit*100)-(share.price*unit*DmatSession.sstCharges));
                    }else{
                        user.accountBalance+=((share.price*unit)-(share.price*unit*DmatSession.transactionCharges)-(share.price*unit*DmatSession.sstCharges));
                    }
                    int balanceUpdate = dao.update(user);
                    if(balanceUpdate>0)
                    {
                        System.out.println("Transaction successful.");
                    }else {
                        System.err.println("Transaction failed!");
                    }
                }else{
                    System.err.println("Transaction failed!");
                }
            }else{
                System.err.println("Transaction failed!");
            }
        }


    public void viewTransactionReport()
    {
        System.out.println("1. To view all transaction");
        System.out.println("2. To view transaction between specific date");
        System.out.println("3. To view transaction for a Share");
        System.out.println("Enter you choice::");
        int choice = Integer.parseInt(scanner.nextLine());

        if(choice==1)
        {
            for(Transaction T:tDao.retrieve()){
                T.prettyPrint();
            }
        }else if(choice==2)
        {
            System.out.println("Enter date1:[YYYY-MM-DD]");
            String date1=scanner.nextLine();
            System.out.println("Enter date2:[YYYY-MM-DD]");
            String date2=scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            try{
                String sql = "SELECT * FROM [Transaction] where transactedOn BETWEEN '"+dateFormat.format(inputFormat.parse(date1))+"' AND '"+dateFormat.format(inputFormat.parse(date2))+"' AND userId="+DmatSession.user.id;
                for(Transaction T:tDao.retrieve(sql)){
                    T.prettyPrint();
                }
            }catch (Exception e)
            {
                System.err.println("Something went wrong!"+e);
            }
        }else if(choice==3)
        {
            System.out.println("Enter shareId");
            int shareId = Integer.parseInt(scanner.nextLine());
            String sql = "SELECT * FROM [Transaction] WHERE shareId="+shareId+" AND userId="+DmatSession.user.id;
            for(Transaction T:tDao.retrieve(sql)){
                T.prettyPrint();
            }
        }else {
            System.err.println("Invalid choice...");
        }


    }
}
