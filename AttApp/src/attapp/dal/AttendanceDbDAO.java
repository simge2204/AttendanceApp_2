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
public class AttendanceDbDAO implements DAOInterface {

    
    
    @Override
    public boolean checkForDailtyAttendance(Calendar date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkForSchoolNetWork() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Student getStudent(int id) throws SQLServerException, IOException, SQLException {
        DbConnection dc = new DbConnection();
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("Select * FROM Student WHERE studentID = (?)");) {
            Student studToGet = null;
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("s_Name");
                int ID = rs.getInt("studentID");
                String email = rs.getString("s_Email");
                int schoolClass = rs.getInt("classID");

                    String sqlkey = "select * from Class inner join Student on Student.classID=Class.classID where Class.classID="+schoolClass ;
           Statement statement = con.createStatement();
          
           ResultSet crs=statement.executeQuery(sqlkey);
           String sc = "";
           while (crs.next()) {
               sc=crs.getString("c_Name"); 
           }
                studToGet = new Student(name, ID, email, sc);

            }
            return studToGet;
        }

    }

    @Override
    public void askForAttendance(int id, Attendance chosenAttendance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Teacher getTeacher(int id) throws SQLServerException, IOException, SQLException {
        DbConnection dc = new DbConnection();
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("Select * FROM Teacher WHERE teacherID = (?)");) {
            Teacher TeachToGet = null;
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("t_Name");
                int ID = rs.getInt("teacherID");
                String email = rs.getString("t_Email");

                TeachToGet = new Teacher(name, ID, email);

            }
            return TeachToGet;
        }

    }

    @Override
    public void removeTeacher(Teacher TeachToRemove) throws IOException, SQLServerException, SQLException {
        int teachId = TeachToRemove.getId();
        DbConnection dc = new DbConnection();

        try (Connection con = dc.getConnection();
                PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM  Teacher WHERE TeacherId= (?)");) {
            pstmt1.setInt(1, teachId);
            pstmt1.execute();

        }
    }

    @Override
    public void removeStudent(Student StudToRemove) throws IOException, SQLServerException, SQLException {
         int studId = StudToRemove.getId();
        DbConnection dc = new DbConnection();

        try (Connection con = dc.getConnection();
                PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM  Student WHERE StudentId= (?)");) {
            pstmt1.setInt(1, studId);
            pstmt1.execute();

        }
    }
    

    @Override
    public Student addStudent(String name, int Id, String Email, int schoolClass) throws IOException, SQLServerException, SQLException {
        DbConnection ds = new DbConnection();
        Student addedStudent = null;

        String SQL = "INSERT INTO Student VALUES (?, ?, ?)";

        try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, name);
//            pstmt.setInt(2, Id);
            pstmt.setString(2, Email);
            pstmt.setInt(3, schoolClass);

            pstmt.execute();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();

            int key=0;
            
            if (generatedKeys.next()) {
              key= generatedKeys.getInt(1);
               
            }
           
            String sqlkey = "select * from Class inner join Student on Student.classID=Class.classID where Class.classID="+key; 
           Statement statement = con.createStatement();
          
           ResultSet rs=statement.executeQuery(sqlkey);
           String sc = "";
           while (rs.next()) {
               sc=rs.getString("c_Name"); 
           }
           
            addedStudent = new Student(name, generatedKeys.getInt(1), Email, sc);
        }

        return addedStudent;
    }

    @Override
    public Teacher addTeacher(String name, int Id, String Email) throws IOException, SQLServerException, SQLException {
        DbConnection ds = new DbConnection();
        Teacher addedTeacher = null;

        String SQL = "INSERT INTO Teacher VALUES (?, ?)";

        try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, name);
//            pstmt.setInt(2, Id);
            pstmt.setString(2, Email);

            pstmt.execute();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                addedTeacher = new Teacher(name, generatedKeys.getInt(1), Email);
            }
        }

        return addedTeacher;
    }

}
