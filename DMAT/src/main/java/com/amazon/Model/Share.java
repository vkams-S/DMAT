package com.amazon.Model;
/*
CREATE TABLE [Share]
(
id INT IDENTITY(1,1),
companyName VARCHAR(256),
price decimal(38,2),
lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(id)
)
 */
public class Share {
    public int id;
    public String companyName;
    public double price;
    public String lastUpdatedOn;

    public Share() {
    }

    public Share(int id, String companyName, double price, String lastUpdatedOn) {
        this.id = id;
        this.companyName = companyName;
        this.price = price;
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @Override
    public String toString() {
        return "Share{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", price='" + price + '\'' +
                ", lastUpdatedOn='" + lastUpdatedOn + '\'' +
                '}';
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Share ID:\t\t"+id);
        System.out.println("Company Name:\t\t"+companyName);
        System.out.println("Price:\t\t"+price);
        System.out.println("Last Updated On :\t"+lastUpdatedOn);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
