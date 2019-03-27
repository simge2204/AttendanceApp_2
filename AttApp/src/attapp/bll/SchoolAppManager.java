package attapp.bll;

import java.sql.Date;
import attapp.be.Attendance;
import attapp.be.Student;
import attapp.be.Teacher;
import attapp.dal.SchoolAppDAL;
import attapp.dal.AttendanceDbDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
}
