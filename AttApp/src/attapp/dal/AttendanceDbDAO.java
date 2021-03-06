/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attapp.dal;

import attapp.be.Attendance;
import attapp.be.SchoolClass;
import attapp.be.Student;
import attapp.be.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author Christian Occhionero
 */
public class AttendanceDbDAO implements DAOInterface 
{
    @Override
    public boolean checkForDailyAttendance(Date date) throws SQLServerException, IOException, SQLException{
        DbConnection dc = new DbConnection();
         boolean wasThere = false;
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("Select * from Attendendance where non_Attendance = (?) join abscent");){
            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                boolean b = rs.getBoolean("abscent");
             wasThere = b;   
            }
        }
        return wasThere;
    }

    @Override
    public boolean checkForSchoolNetWork() {

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return true; 

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
            pstmt.setString(2, Email);

            pstmt.execute();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                addedTeacher = new Teacher(name, generatedKeys.getInt(1), Email);
            }
        }

        return addedTeacher;
    }
    

  
    public Attendance addAttendanceDays(int id) throws SQLServerException, SQLException
    {
        Attendance at = null;
        DbConnection ds = new DbConnection();
        String SQL = "INSERT INTO Attendance (studentID, attendanceDay, attendance) VALUES ((?), GETDATE(), (?))";
        try(Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL);)
        {
            pstmt.setInt(1,id);
            pstmt.setBoolean(2, false);
            
             pstmt.execute();
          
        }
        return at;
    }
    
    public void editAttendance(int id, String date) throws SQLServerException, SQLException, IOException
    {   
        DbConnection dc = new DbConnection();
        try(Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("UPDATE Attendance SET attendance = '1' "
                + "WHERE studentID = (?) AND attendanceDay = (?); ");)
        {
            pstmt.setInt(1, id);
            pstmt.setString(2, date.toString());
            pstmt.execute();
        }
    }
    
     public ArrayList<Attendance> getAttendance(int studId)throws SQLServerException, IOException, SQLException{
        DbConnection dc = new DbConnection();
       ArrayList<Attendance> attenList = new ArrayList<>();
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("select * from Attendance where studentID=(?)  ;")) {
            Attendance AttenToGet = null;
              pstmt.setInt(1, studId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Date dateo = rs.getDate("attendanceDay");
                Boolean wasThere = rs.getBoolean("attendance");
                AttenToGet = new Attendance(dateo, wasThere);
                attenList.add(AttenToGet);
            }
            return attenList;

        }
     }

     
//       public ArrayList<SchoolClass> getClassList( int teachId)throws SQLServerException, IOException, SQLException{
//        DbConnection dc = new DbConnection();
//      ArrayList<SchoolClass> classList = new ArrayList<>();
//        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("select * from Class inner join TeacherClass on Class.classID = TeacherClass.classID where teacherID = (?);")) {
//            SchoolClass classToGet = null;
//              pstmt.setInt(1, teachId);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//               String name = rs.getString("c_Name");
//              
//              
//                classToGet = new SchoolClass(name);
//                classList.add(classToGet);
//            }
//            return classList;
//        }
//        }
        
       public List getAllClasses(int teachId) throws IOException, SQLException{
        DbConnection dc = new DbConnection();
      ArrayList<SchoolClass> classList = new ArrayList<>();
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("select * from Class inner join TeacherClass on Class.classID = TeacherClass.classID where teacherID = (?);")) {
            SchoolClass classToGet = null;
              pstmt.setInt(1, teachId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
               String name = rs.getString("c_Name");
              int id = rs.getInt("classID");
              
                classToGet = new SchoolClass(name,id);
                classList.add(classToGet);
            }
            return classList;

        }
     
     }
       
          public List getAllStudents(int id) throws IOException, SQLException{
        DbConnection dc = new DbConnection();
      ArrayList<Student> studentList = new ArrayList<>();
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("select Student.studentID from Student where classID=(?)")) {
           Student sToGet = null;
              pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
               int sId = rs.getInt("studentID");
              sToGet = getStudent(sId); 
               studentList.add(sToGet);
                    
                }
  
                
            }
            return studentList;

        }
     

     
}
