package com.amazon.DB;

import com.amazon.Model.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    private UserDAO()
    {

    }
    private static UserDAO dao = new UserDAO();
    public static UserDAO getInstance()
    {
        return dao;
    }
    DB db = DB.getInstance();
    @Override
    public int insert(User object) {
        String sql = "INSERT INTO [User] (userName, accountName,name, email,phone, password, accountBalance) VALUES ('"+object.userName+"', '"+object.accountName+"', '"+object.name+"', '"+object.email+"', '"+object.phone+"', '"+object.password+"', "+object.accountBalance+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(User object) {
        String sql = "UPDATE [User] set userName = '"+object.userName+"', accountName='"+object.accountName+"', name='"+object.name+"', phone='"+object.phone+"', accountBalance='"+object.accountBalance+"' WHERE email = '"+object.email+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(User object) {
        String sql = "DELETE FROM [User] WHERE email = '"+object.email+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<User> retrieve() {
        String sql = "SELECT * FROM [User]";
        ResultSet set = db.executeQuery(sql);
        ArrayList<User> users = new ArrayList<>();
        try {
            while (set.next()) {
                User user = new User();
                user.id = set.getInt("id");
                user.name = set.getString("name");
                user.phone = set.getString("phone");
                user.email = set.getString("email");
                user.password = set.getString("password");
                user.userName = set.getString("userName");
                user.accountName = set.getString("accountName");
                user.accountBalance = set.getDouble("accountBalance");
                user.lastUpdatedOn = set.getString("lastUpdatedOn");
                users.add(user);
            }

        } catch (Exception e) {
            System.err.println("Something went wrong..." + e);
        }
        return users;
    }

    @Override
    public List<User> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<User> users = new ArrayList<>();
        try {
            while (set.next()) {
                User user = new User();
                user.id = set.getInt("id");
                user.name = set.getString("name");
                user.phone = set.getString("phone");
                user.email = set.getString("email");
                user.password = set.getString("password");
                user.userName = set.getString("userName");
                user.accountName = set.getString("accountName");
                user.accountBalance = set.getInt("accountBalance");
                user.lastUpdatedOn = set.getString("lastUpdatedOn");
                users.add(user);
            }

        } catch (Exception e) {
            System.err.println("Something went wrong..." + e);
        }
        return users;
    }
}
