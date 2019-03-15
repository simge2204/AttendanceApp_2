/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attapp.dal;

import attapp.be.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Caspe
 */
public class Tester
{
    public static void main(String[] args) throws IOException, SQLServerException, SQLException
    {
        
        
        DbConnection db = new DbConnection();
        Connection con = db.getConnection();
        try
        {
            String SQL2 = "INSERT INTO Class Values(?, ?)";
            PreparedStatement pstmt2 = con.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
            pstmt2.setString(1,"CS2018");
            pstmt2.setString(2,"ITO");
            pstmt2.execute();
            
            String SQL = "INSERT INTO Student VALUES (?, ?, ?)";
          
            PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,101);
            pstmt.setString(2,"Casper Henriksen");
            pstmt.setString(3,"casp7363@easv365.dk");
            pstmt.execute(); 
            
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
     
        }
    }
   
}
