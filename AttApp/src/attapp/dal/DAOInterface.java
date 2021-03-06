package attapp.dal;

import java.util.Calendar;
import attapp.be.Attendance;
import attapp.be.Student;
import attapp.be.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author Philip
 */
public interface DAOInterface
{

    /**
     * Returns false if the user hasn't registered his/hers attendance
     *
     * @param date
     * @return
     */
    boolean checkForDailyAttendance(Date date)throws SQLServerException, IOException, SQLException;

    /**
     * Returns true if the user is on the school network
     *
     * @return
     */

    boolean checkForSchoolNetWork();

    /**
     * Asks for a change of attendance
     * @param id
     * @param chosenAttendance 
     */
    
    void removeStudent(Student StudToRemove)throws IOException, SQLServerException, SQLException;
    
    void askForAttendance(int id, Attendance chosenAttendance);
    

    Teacher getTeacher(int id)throws SQLServerException, IOException, SQLException;
     
    
    Student getStudent(int id) throws SQLServerException, IOException, SQLException;
    
    void removeTeacher(Teacher TeachToRemove)throws IOException, SQLServerException, SQLException; 
    
    Student addStudent(String name, int Id, String Email, int schoolClass)throws IOException, SQLServerException, SQLException;
    
    Teacher addTeacher(String name,int Id,String Email)throws IOException, SQLServerException, SQLException;





}
