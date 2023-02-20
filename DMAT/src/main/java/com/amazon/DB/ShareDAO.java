package com.amazon.DB;

import com.amazon.Model.Share;
import com.amazon.Model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShareDAO implements DAO<Share> {
    private ShareDAO(){}
    private static ShareDAO shareDAO = new ShareDAO();
    public static ShareDAO getInstance()
    {
        return shareDAO;
    }
    DB db = DB.getInstance();
    @Override
    public int insert(Share object) {
        String sql = "INSERT INTO [Share] (companyName, price,lastUpdatedOn) VALUES ('"+object.companyName+"', "+object.price+", '"+object.lastUpdatedOn+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Share object) {
        String sql = "UPDATE [Share] set companyName = '"+object.companyName+"', price="+object.price+" WHERE id = "+object.id;
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Share object) {
        String sql = "DELETE FROM [Share] WHERE id = " + object.id ;
        return db.executeSQL(sql);
    }

    @Override
    public List<Share> retrieve() {
        String sql = "SELECT * FROM [Share]";
        ResultSet set = db.executeQuery(sql);
        ArrayList<Share> shares = new ArrayList<>();
        try {
            while (set.next()) {
                Share share = new Share();
                share.id = set.getInt("id");
                share.companyName = set.getString("companyName");
                share.price = set.getDouble("price");
                share.lastUpdatedOn = set.getString("lastUpdatedOn");
                shares.add(share);
            }

        } catch (Exception e) {
            System.err.println("Something went wrong..." + e);
        }
        return shares;
    }

    @Override
    public List<Share> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Share> shares = new ArrayList<>();
        try {
            while (set.next()) {
                Share share = new Share();
                share.id = set.getInt("id");
                share.companyName = set.getString("companyName");
                share.price = set.getDouble("price");
                share.lastUpdatedOn = set.getString("lastUpdatedOn");
                shares.add(share);
            }

        } catch (Exception e) {
            System.err.println("Something went wrong..." + e);
        }
        return shares;
    }
}
