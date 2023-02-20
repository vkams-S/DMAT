package com.amazon.Model;
/*
CREATE TABLE [Transaction]
(
id INT IDENTITY(1,1),
userId INT,
shareId INT ,
shareCount INT,
pricePerShare DECIMAL(38,2),
transactedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
sstCharges DECIMAL(38,2),
transactionCharges DECIMAL(38,2),
type INT,
PRIMARY KEY (id),
FOREIGN KEY (shareId) REFERENCES [Share](id),
FOREIGN KEY (userId) REFERENCES [User](id)
)
 */
public class Transaction {
    public int id;
    public int userId;
    public int shareId;
    public int shareCount;
    public double pricePerShare;
    public String transactedOn;
    public double transactionCharges;
    public double sstCharges;
    public int type; //1. buy 2. sell

    public Transaction() {
    }

    public Transaction(int id,int userId, int shareId, int shareCount, double pricePerShare, String transactedOn, double transactionCharges, double sstCharges, int type) {
        this.id = id;
        this.userId= userId;
        this.shareId = shareId;
        this.shareCount = shareCount;
        this.pricePerShare = pricePerShare;
        this.transactedOn = transactedOn;
        this.transactionCharges = transactionCharges;
        this.sstCharges = sstCharges;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", shareId=" + shareId +
                ", shareCount=" + shareCount +
                ", pricePerShare=" + pricePerShare +
                ", transactedOn='" + transactedOn +
                ", transactionCharges=" + transactionCharges +
                ", sstCharges=" + sstCharges +
                ", type=" + type +
                '}';
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Transaction ID:\t\t"+id);
        System.out.println("Transacted By:\t\t"+userId);
        System.out.println("Share Id:\t\t"+shareId);
        System.out.println("Share count:\t\t"+shareCount);
        System.out.println("price Per Share:\t\t"+pricePerShare);
        System.out.println("Transaction Charges:\t\t"+transactionCharges);
        System.out.println("Security Transfer Tax:\t\t"+sstCharges);
        System.out.println("Traction Type:\t\t"+((type==1)?"BOUGHT":"SOLD"));
        System.out.println("Traction DATE/TIME:\t\t"+transactedOn);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
