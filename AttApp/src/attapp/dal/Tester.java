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
           
             
            
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
     
        }
    }
   public Student createStudent(Student studentToAdd) throws IOException, SQLServerException, SQLException
   {
        try
        {
            String name = studentToAdd.getName();
            String email = studentToAdd.getEmail();
            String schoolClass = studentToAdd.getSchoolClass();
            
            Student newStudent = null;
            DbConnection dbCon = new DbConnection();
            Connection con = dbCon.getConnection();
            String SQL = "INSERT INTO Student VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, schoolClass);
            pstmt.execute();
            
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next())
            {
                newStudent= new Student(name, generatedKeys.getInt(1), email, schoolClass);
                return newStudent;
            }
            return newStudent;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
        
   }
}
