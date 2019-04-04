/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attapp.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * Connects to Database with database information.
 * @author Philip
 */
public class DbConnection

{
    private SQLServerDataSource ds = new SQLServerDataSource();

    public DbConnection()
    {
        setupDataSource();
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
    
    private  void setupDataSource(){
     ds.setDatabaseName("AttendanceAppDatabase");
     ds.setUser("CS2018A_6");
     ds.setPassword("CS2018A_6");
     ds.setPortNumber(1433);
     ds.setServerName("10.176.111.31");
    }
//    private static final String PROP_FILE = "C:/Github2/AttendanceApp_2/AttApp/src/attapp/data/database.info";
//    private final SQLServerDataSource ds;
//
//    public DbConnection() throws IOException
//    {
//        Properties databaseProperties = new Properties();
//        databaseProperties.load(new FileInputStream(PROP_FILE));
//        ds = new SQLServerDataSource();
//        ds.setServerName(databaseProperties.getProperty("Server"));
//        ds.setDatabaseName(databaseProperties.getProperty("Database"));
//        ds.setUser(databaseProperties.getProperty("User"));
//        ds.setPassword(databaseProperties.getProperty("Password"));
//    }
//
//    public Connection getConnection() throws SQLServerException
//    {
//        return ds.getConnection();
//    }
}