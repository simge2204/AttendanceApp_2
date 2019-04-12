package attapp.bll;

import java.sql.Date;
import attapp.be.Attendance;
import attapp.be.SchoolClass;
import attapp.be.Student;
import attapp.be.Teacher;
import attapp.dal.AttendanceDbDAO;
import attapp.dal.Authentication;

import com.microsoft.sqlserver.jdbc.SQLServerException;
//import attapp.dal.SchoolAppDAL;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Christian Occhionero
 */
public class SchoolAppManager
{
    private final Authentication authentication;

    private final AttendanceDbDAO dal;


    public SchoolAppManager() throws IOException
    {
        authentication = new Authentication();

        this.dal = new AttendanceDbDAO();
    }
    
  
    
    public Student getStudent(String username, String password) throws IOException, SQLException

    {
        return dal.getStudent(0);
    }
    
    public Student getStudent(int id) throws IOException, SQLException
    {
        return dal.getStudent(id);
    }

    
    
    public Student getLoginStudent (String username, String password) throws SQLException{
        return authentication.validateStudentLogin(username, password);
    }

    public boolean checkForSchoolNetwork()
    {
        return dal.checkForSchoolNetWork();
               
    }

    public boolean checkForDailyAttendance(Date date) throws IOException, SQLException
    {
       return dal.checkForDailyAttendance(date);
    }

    public void askForAttendance(int id, Attendance chosenAttendance)
    {
        dal.askForAttendance(id, chosenAttendance);
    }

    public Teacher getLoginTeacher(String username, String password) throws SQLException, IOException
    {
       return authentication.validateTeacherLogin(username, password);
    }

  
    public ArrayList<Attendance>  getAttendance( int studId)throws SQLServerException, IOException, SQLException{
        return dal.getAttendance(studId);
    }

//    public Teacher getTeacher(String username, String password) {
//        return dal.getTeacher();
//    }

    public void editAttendance(int id, String date) throws SQLException, SQLServerException, IOException
    {
        dal.editAttendance(id, date);
    }

    public Attendance addAttendanceDays(int id) throws SQLServerException, SQLException
    {
        return dal.addAttendanceDays(id);
    }
    
//    public ArrayList<SchoolClass> getClassList(int id) throws IOException, SQLException{
//    
//        return dal.getClassList(id);
//}
        
     public List<SchoolClass>  getAllClasses( int teachId)throws SQLServerException, IOException, SQLException{
        return dal.getAllClasses(teachId);
        }
        
    public List getAllStudents(int id) throws IOException, SQLException    {
        return dal.getAllStudents(id);
    }

}
