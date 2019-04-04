package attapp.bll;

import java.sql.Date;
import attapp.be.Attendance;
import attapp.be.Student;
import attapp.be.Teacher;
import attapp.dal.AttendanceDbDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Christian Occhionero
 */
public class SchoolAppManager
{

    private final AttendanceDbDAO dal;

    public SchoolAppManager()
    {
        this.dal = new AttendanceDbDAO();
    }
    
    public Student getStudent(int id) throws IOException, SQLException
    {
        return dal.getStudent(id);
    }
//    public List<Student> getStudent() throws IOException, SQLException
//    {
//    Student s1 = manager.getStudent(1); 
//     s=s1;
//     return s1;
//    }
    
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

    public Teacher getTeacher(int id) throws IOException, SQLException 
    {
       return dal.getTeacher(id);
    }
    
    public ArrayList<Attendance>  getAttendance( int studId)throws SQLServerException, IOException, SQLException{
        return dal.getAttendance(studId);
    }
    
    public void addStudent(String name, int Id, String Email, int schoolClass) throws IOException, SQLException
    {
        dal.addStudent(name, Id, Email, schoolClass);
    }
}
