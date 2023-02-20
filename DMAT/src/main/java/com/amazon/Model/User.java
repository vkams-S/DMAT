package com.amazon.Model;
/*
CREATE TABLE [User]
(
id INT IDENTITY(1,1),
userName VARCHAR(256),
accountName VARCHAR(256),
name VARCHAR(256),
email VARCHAR(256) UNIQUE,
phone VARCHAR(256) UNIQUE,
password VARCHAR(256),
accountBalance decimal(38,2),
lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(id)
)
 */
public class User {
    public int id;
    public String userName;
    public String accountName;
    public String name;
    public String email;
    public String phone;
    public String password;
    public double accountBalance;
    public String lastUpdatedOn;

    public User() {
    }

    public User(int id, String userName, String accountName, String name, String email, String phone, String password, double accountBalance, String lastUpdatedOn) {
        this.id = id;
        this.userName = userName;
        this.accountName = accountName;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.accountBalance = accountBalance;
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", accountBalance=" + accountBalance +
                ", lastUpdatedOn='" + lastUpdatedOn + '\'' +
                '}';
    }
}
