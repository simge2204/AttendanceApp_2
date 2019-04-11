package attapp.bll;

import java.sql.Date;
import attapp.be.Attendance;
import attapp.be.Student;
import attapp.be.Teacher;
import attapp.dal.AttendanceDbDAO;
import attapp.dal.Authentication;
import attapp.dal.SchoolAppDAL;
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
    private final Authentication authentication;
    private final SchoolAppDAL dal;
    private final AttendanceDbDAO Attend;

    public SchoolAppManager() throws IOException
    {
        authentication = new Authentication();
        this.dal = new SchoolAppDAL();
        Attend = new AttendanceDbDAO();
    }
    
    public Student getStudent(String username, String password)
    {
        return dal.getStudent(0);
    }
    
    public Student getLoginStudent (String username, String password) throws SQLException{
        return authentication.validateStudentLogin(username, password);
    }

    public boolean checkForSchoolNetwork()
    {
        return dal.checkForSchoolNetWork();
               
    }

    public boolean checkForDailyAttendance(Date date)
    {
       return dal.checkForDailyAttendance(date);
    }

    public void askForAttendance(int id, Attendance chosenAttendance)
    {
        dal.askForAttendance(id, chosenAttendance);
    }

    public Teacher getLoginTeacher(String username, String password) throws SQLException
    {
       return authentication.validateTeacherLogin(username, password);
    }

    public Teacher getTeacher(String username, String password) {
        return dal.getTeacher();
    }

    public void editAttendance(int id, Date date) throws SQLException, SQLServerException, IOException
    {
        Attend.editAttendance(id, date);
    }

    public ArrayList<Attendance> getAttendance(int studId) throws IOException, SQLException
    {
        return Attend.getAttendance(studId);
    }
    
    
}
