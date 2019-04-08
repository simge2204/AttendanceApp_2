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
 *
 * @author Philip
 */
public class DbConnection {

    private static final String SERVER_NAME = "10.176.111.31";
    private static final String DATABASE_NAME = "AttendanceAppDatabase";
    private static final String USER = "CS2018A_6";
    private static final String PASSWORD = "CS2018A_6";

    private static SQLServerDataSource ds;

    public DbConnection(){
        ds = new SQLServerDataSource();
        ds.setServerName(SERVER_NAME);
        ds.setDatabaseName(DATABASE_NAME);
        ds.setUser(USER);
        ds.setPassword(PASSWORD);
    }

    public Connection getConnection() throws SQLServerException {
        Connection con = ds.getConnection();
        return con;
    }
}
