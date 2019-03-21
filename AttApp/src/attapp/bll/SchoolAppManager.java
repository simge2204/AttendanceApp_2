package attapp.bll;

import java.sql.Date;
import attapp.be.Attendance;
import attapp.be.Student;
import attapp.be.Teacher;
import attapp.dal.SchoolAppDAL;

/**
 *
 * @author Christian Occhionero
 */
public class SchoolAppManager
{

    private final SchoolAppDAL dal;

    public SchoolAppManager()
    {
        this.dal = new SchoolAppDAL();
    }
    
    public Student getStudent(int id)
    {
        return dal.getStudent(id);
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

    public Teacher getTeacher()
    {
       return dal.getTeacher();
    }
}
