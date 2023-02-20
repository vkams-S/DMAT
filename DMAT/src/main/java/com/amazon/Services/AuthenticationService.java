package com.amazon.Services;

import com.amazon.DB.UserDAO;
import com.amazon.Model.User;

import java.util.List;

public class AuthenticationService {
    private static AuthenticationService service = new AuthenticationService();
    public static AuthenticationService getInstance()
    {
        return service;
    }
    UserDAO dao = UserDAO.getInstance();
    private AuthenticationService(){

    }

    public boolean loginUser(User user)
    {
        String SQL = "SELECT * FROM [User] WHERE id = "+user.id+" AND password = '"+user.password+"'";
        List<User> users = dao.retrieve(SQL);
        if(users.size()>0)
        {
            User u = users.get(0);
            user.id = u.id;
            user.userName = u.userName;
            user.accountName = u.accountName;
            user.name = u.name;
            user.email = u.email;
            user.phone = u.phone;
            user.password = u.password;
            user.accountBalance = u.accountBalance;
            user.lastUpdatedOn = u.lastUpdatedOn;
            return true;
        }
        return false;
    }

    public boolean registerUser(User user)
    {
        return dao.insert(user)>0;
    }

    public boolean updateUser(User user)
    {
        return dao.update(user)>0;
    }

}
