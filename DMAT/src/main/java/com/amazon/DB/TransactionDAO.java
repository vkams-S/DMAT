package com.amazon.DB;

import com.amazon.Model.Transaction;
import com.amazon.Model.User;
import com.amazon.Session.DmatSession;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements DAO<Transaction> {
    private TransactionDAO(){}
    private static TransactionDAO transactionDAO = new TransactionDAO();
    public static TransactionDAO getInstance()
    {
        return transactionDAO;
    }
    DB db = DB.getInstance();
    @Override
    public int insert(Transaction object) {
        String sql = "INSERT INTO [Transaction] (userId,shareId, shareCount,pricePerShare,sstCharges, type,transactionCharges) VALUES ("+object.userId+", "+object.shareId+", "+object.shareCount+", "+object.pricePerShare+", "+object.sstCharges+", "+object.type+", "+object.transactionCharges+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Transaction object) {
        String sql = "UPDATE [Transaction] set userId="+object.userId+", shareId = "+object.shareId+", shareCount="+object.shareCount+", pricePerShare="+object.pricePerShare+"', sstCharges="+object.sstCharges+", type="+object.type+", transactionCharges="+object.transactionCharges+" WHERE id = "+object.id;
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Transaction object) {
        String sql = "DELETE FROM [Transaction] WHERE id = "+object.id;
        return db.executeSQL(sql);
    }

    @Override
    public List<Transaction> retrieve() {
        String sql = "SELECT * FROM [Transaction] WHERE userId="+ DmatSession.user.id;
        ResultSet set = db.executeQuery(sql);
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            while (set.next()) {
                Transaction transaction = new Transaction();
                transaction.id = set.getInt("id");
                transaction.userId = set.getInt("userId");
                transaction.shareId = set.getInt("shareId");
                transaction.shareCount = set.getInt("shareCount");
                transaction.pricePerShare = set.getDouble("pricePerShare");
                transaction.transactedOn = set.getString("transactedOn");
                transaction.sstCharges = set.getDouble("sstCharges");
                transaction.transactionCharges = set.getDouble("transactionCharges");
                transaction.type = set.getInt("type");
                transactions.add(transaction);
            }

        } catch (Exception e) {
            System.err.println("Something went wrong..." + e);
        }
        return transactions;
    }

    @Override
    public List<Transaction> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            while (set.next()) {
                Transaction transaction = new Transaction();
                transaction.id = set.getInt("id");
                transaction.userId = set.getInt("userId");
                transaction.shareId = set.getInt("shareId");
                transaction.shareCount = set.getInt("shareCount");
                transaction.pricePerShare = set.getDouble("pricePerShare");
                transaction.transactedOn = set.getString("transactedOn");
                transaction.sstCharges = set.getDouble("sstCharges");
                transaction.transactionCharges = set.getDouble("transactionCharges");
                transaction.type = set.getInt("type");
                transactions.add(transaction);
            }

        } catch (Exception e) {
            System.err.println("Something went wrong..." + e);
        }
        return transactions;
    }
}
