package com.amazon.DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    public static String FILEPATH ="C:/Users/vkams/Desktop/DeskTop/ATLAS/Final project/Project-1/DMAT-VKAMS/DMAT/src/main/java/com/amazon/dbconfig.txt";
    public static String URL="";
    public static String USER="";
    public static String PASSWORD="";

    Connection connection;
    Statement statement;


    private DB(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("[DB] Driver loaded successfully.");
            createConnection();
        }catch (Exception e){
            System.err.println("Something went wrong!"+e);
        }
    }

    private void createConnection()
    {
        try{
            File file =new File(FILEPATH);
            if(file.exists())
            {
                FileReader reader =new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);
                URL = buffer.readLine();
                USER = buffer.readLine();
                PASSWORD = buffer.readLine();
            buffer.close();
            reader.close();
            System.out.println("DB connection configured successfully using file.");
            }else{
                System.err.println("Cannot read DBConfig file.");
            }

        connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connection created Successfully.");
        }catch (Exception e)
        {
            System.err.println("[DB(createConnection)]Something went wrong."+e);
        }
    }

    public int executeSQL(String sql)
    {
        int result = 0;
        try{
            System.out.println("[DB(executeSQL)] Executing SQL query| "+sql);
            statement =connection.createStatement();
            result = statement.executeUpdate(sql);
            System.out.println("[DB(executeSQL)] Query executed successfully");
        }catch (Exception e)
        {
            System.err.println("[DB(executeSQL)] Something went wrong!");
        }

        return result;
    }

    public ResultSet executeQuery(String sql)
    {
        ResultSet result = null;
        try{
            System.out.println("[DB(executeSQL)] Executing SQL query| "+sql);
            statement =connection.createStatement();
            result = statement.executeQuery(sql);
            System.out.println("[DB(executeSQL)] Query executed successfully");
        }catch (Exception e)
        {
            System.err.println("[DB(executeSQL)] Something went wrong!");
        }

        return result;
    }

    // Make the object creation singleton
    private static DB db= new DB();

    public static DB getInstance()
    {
        return db;
    }

    public void closeConnection()
    {
        try{
            connection.close();
            System.out.println("[DB(closeConnection)] DB Connection closed successfully.");
        }catch (Exception e)
        {
            System.err.println("[DB(closeConnection)] Something went wrong!"+e);
        }
    }



}
