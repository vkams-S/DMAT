package com.amazon.DB;

import com.amazon.Model.User;
import com.amazon.Model.UserShares;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserShareDAO implements DAO<UserShares> {
    private UserShareDAO(){}
    private static UserShareDAO userShareDAO = new UserShareDAO();
    public static UserShareDAO getInstance()
    {
        return userShareDAO;
    }
    DB db = DB.getInstance();
    @Override
    public int insert(UserShares object) {
        String sql = "INSERT INTO [UserShares] (userId, shareId,companyName, shareCount,price) VALUES ("+object.userId+", "+object.shareId+", '"+object.companyName+"', "+object.shareCount+", "+object.price+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(UserShares object) {
        String sql = "UPDATE [UserShares] set userId ="+object.userId+", shareId = " + object.shareId + ", companyName='" + object.companyName + "', shareCount="+object.shareCount  + ", price="+object.price + " WHERE id = " + object.id;
        return db.executeSQL(sql);
    }

    @Override
    public int delete(UserShares object) {
        String sql = "DELETE FROM [UserShares] WHERE id = "+object.id;
        return db.executeSQL(sql);
    }

    @Override
    public List<UserShares> retrieve() {
        String sql = "SELECT * FROM [UserShares]";
        ResultSet set = db.executeQuery(sql);
        ArrayList<UserShares> userShares = new ArrayList<>();
        try {
            while (set.next()) {
                UserShares userShare = new UserShares();
                userShare.id = set.getInt("id");
                userShare.shareId = set.getInt("shareId");
                userShare.companyName = set.getString("companyName");
                userShare.shareCount = set.getInt("shareCount");
                userShare.price = set.getInt("price");
                userShares.add(userShare);
            }

        } catch (Exception e) {
            System.err.println("Something went wrong..." + e);
        }
        return userShares;
    }

    @Override
    public List<UserShares> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<UserShares> userShares = new ArrayList<>();
        try {
            while (set.next()) {
                UserShares userShare = new UserShares();
                userShare.id = set.getInt("id");
                userShare.shareId = set.getInt("shareId");
                userShare.companyName = set.getString("companyName");
                userShare.shareCount = set.getInt("shareCount");
                userShare.price = set.getInt("price");
                userShares.add(userShare);
            }

        } catch (Exception e) {
            System.err.println("Something went wrong..." + e);
        }
        return userShares;
    }
}
