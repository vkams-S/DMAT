package com.amazon.Model;
/*
CREATE TABLE [UserShares]
(
id INT IDENTITY(1,1),
userId INT,
shareId INT,
companyName VARCHAR(256),
shareCount INT,
PRIMARY KEY(id),
FOREIGN KEY(userId) REFERENCES [User](id),
FOREIGN KEY(shareId) REFERENCES[share](id)
)
 */
public class UserShares {
    public int id;
    public int userId;
    public int shareId;
    public String companyName;
    public double price;
    public int shareCount;

    public UserShares() {
    }

    public UserShares(int id, int userId, int shareId, String companyName,double price, int shareCount) {
        this.id = id;
        this.userId = userId;
        this.shareId = shareId;
        this.companyName = companyName;
        this.price = price;
        this.shareCount = shareCount;
    }

    @Override
    public String toString() {
        return "UserShares{" +
                "id=" + id +
                ", userId=" + userId +
                ", shareId=" + shareId +
                ", companyName='" + companyName + '\'' +
                ", price='" + price + '\'' +
                ", shareCount=" + shareCount +
                '}';
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Share ID:\t\t"+shareId);
        System.out.println("Company Name:\t\t"+companyName);
        System.out.println("Share count:\t\t"+shareCount);
        System.out.println("Price:\t\t"+price);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
