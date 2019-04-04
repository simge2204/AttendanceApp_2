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
import java.sql.Date;
<<<<<<< HEAD
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
=======
>>>>>>> parent of d91a0ee... Update AttendanceDbDAO.java
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian Occhionero
 */
public class AttendanceDbDAO implements DAOInterface {

    private Student currentStudent;

    @Override
    public boolean checkForDailyAttendance(Date date) throws SQLServerException, IOException, SQLException{
        DbConnection dc = new DbConnection();
         boolean wasThere = false;
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("Select * from Attendendance where non_Attendance = (?) join absent");){
            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                boolean b = rs.getBoolean("absent");
             wasThere = b;
            }
        }
        return wasThere;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    @Override
    public boolean checkForSchoolNetWork() {
        return true;
    }

//    public List<Student> getStudent() throws IOException, SQLException
//    {
//        DbConnection ds = new DbConnection();
//        try (Connection con = ds.getConnection()) {
//            String query = "SELECT * FROM [Student]";
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//
//            ArrayList<Student> students = new ArrayList<>();
//            while (rs.next()) {
//
//                String studentString = "";
//                studentString += rs.getString("id");
//                studentString += rs.getString("s_Name");
//                studentString += rs.getString("s_Email");
//                studentString += rs.getString("password");
//                studentString += rs.getString("classid");
//
//                students.add(
//                        rs.getString("studID"),
//                        rs.getString("s_Name"),
//                        rs.getString("s_Email"),
//                        rs.getString("password"),
//                        rs.getString("classid"));
//            }
//            return students;
//             } catch (SQLException sqle) {
//            System.err.println(sqle);
//            return null;
//        }
//    }

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
                studToGet = new Student(name, ID, email, ""+schoolClass);
//                    String sqlkey = "select * from Class inner join Student on Student.classID=Class.classID where Class.classID="+schoolClass ;
//           Statement statement = con.createStatement();

//           ResultSet crs=statement.executeQuery(sqlkey);
//           String sc = "";
//           while (crs.next()) {
//               sc=crs.getString("c_Name");
//           }
//                studToGet = new Student(name, ID, email, sc);

            }
            return studToGet;
        }

    }

    @Override
    public void askForAttendance(int id, Attendance attendance) {
        DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
        String strDate = dateFormat.format(attendance);
        DbConnection dc = new DbConnection();
        try {
            Connection con = dc.getConnection();
            PreparedStatement pstmt;
            pstmt = con.prepareStatement("Select * FROM Attendance WHERE studentID = (?)");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
//                attendance = rs.getString(strDate);
                id = rs.getInt("studentID");
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        public ArrayList<Attendance> getAttendance( int studId)throws SQLServerException, IOException, SQLException{
        DbConnection dc = new DbConnection();
       ArrayList<Attendance> attenList = new ArrayList<>();
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("Select * FROM Attendance Join Student on Student.studentID = Attendance.studentID where Student.studentID = (?) ;")) {
            Attendance AttenToGet = null;
              pstmt.setInt(1, studId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate("non_Attendance");
                Boolean wasThere = rs.getBoolean("absent");

                AttenToGet = new Attendance(date, wasThere);
                attenList.add(AttenToGet);
            }
            return attenList;
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


}
