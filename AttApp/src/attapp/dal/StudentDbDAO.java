/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attapp.dal;

import attapp.be.Attendance;
import attapp.be.Student;
import attapp.be.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian Occhionero
 */
public class StudentDbDAO implements DAOInterface {
   
            
           
            
            
    @Override
    public boolean checkForDailtyAttendance(Calendar date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkForSchoolNetWork() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Student getStudent(int id) throws SQLServerException, IOException, SQLException {
         DbConnection dc = new DbConnection();
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("Select * FROM Student WHERE studentID = (?)");)
        {
            Student studToGet = null;
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String name = rs.getString("name");
                int ID = rs.getInt("studentID");
                String email = rs.getString("Email");
                String schoolClass=rs.getString("SchoolClass");
                   
                studToGet = new Student(name, ID, email, schoolClass);

            }
            return studToGet;
        }

    }

          
            
            
            
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        } catch (IOException ex) {
//            Logger.getLogger(StudentDbDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Override
    public void askForAttendance(int id, Attendance chosenAttendance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
